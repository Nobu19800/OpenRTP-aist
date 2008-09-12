package jp.go.aist.rtm.repositoryView.ui.views;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import jp.go.aist.rtm.repositoryView.RepositoryViewPlugin;
import jp.go.aist.rtm.repositoryView.model.RTCRVLeafItem;
import jp.go.aist.rtm.repositoryView.repository.RTRepositoryAccesser;
import jp.go.aist.rtm.repositoryView.ui.ArrayContentProvider;
import jp.go.aist.rtm.repositoryView.ui.RepositoryViewLabelProvider;
import jp.go.aist.rtm.repositoryView.ui.preference.RepositoryViewPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.ui.views.propertysheetview.RtcPropertySheetPage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;
import org.eclipse.ui.views.properties.IPropertySheetPage;


public class RepositoryView extends org.eclipse.ui.part.ViewPart {

	private TreeViewer viewer;
	
	private DrillDownAdapter drillDownAdapter;

	private static Log Logger = LogFactory.getLog("Sample");
	
	public RepositoryView() {
		Properties props = System.getProperties();
		try {
			String propPath = RepositoryViewPreferenceManager.getInstance().getPropertyFile_Location();
			if( !(propPath==null || propPath.equals("")) ) {
				props.load(new FileInputStream(propPath));
				props.setProperty("repository.properties", propPath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperties(props);
		//
	}

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent,SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new RepositoryViewLabelProvider());
		
		getViewSite().setSelectionProvider(viewer);
		hookContextMenu();

		viewer.addDragSupport(DND.DROP_COPY | DND.DROP_MOVE,
				new Transfer[] { LocalSelectionTransfer.getInstance() },
				new DragSourceAdapter() {
					@Override
					public void dragStart(DragSourceEvent event) {
						super.dragStart(event);
//						if (AdapterUtil.getAdapter(
//								((IStructuredSelection) viewer.getSelection())
//										.getFirstElement(), Component.class) == null) {
//							event.doit = false;
//						}

						dragSetData(event);
					}

					@Override
					public void dragSetData(DragSourceEvent event) {
						IStructuredSelection structuredSelection = ((IStructuredSelection) viewer
								.getSelection());

						event.data = structuredSelection;
						LocalSelectionTransfer.getInstance().setSelection(
								structuredSelection);

						super.dragSetData(event);
					}
				});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				if( selection.getFirstElement() instanceof RTCRVLeafItem ) {
					RTCRVLeafItem selected = (RTCRVLeafItem)selection.getFirstElement();
					ComponentSpecification component = selected.getComponent();
					if( component.isSpecUnLoad() ) {
						ComponentSpecification new_component = null;
						try {
							new_component = RTRepositoryAccesser.getInstance().getComponentProfile(component);
						} catch (Exception e) {
							MessageDialog.openError(getSite().getShell(), "�G���[",	
							"Profile �̎擾�Ɏ��s���܂���");
							return;
						}
						new_component.setSpecUnLoad(false);
						selected.setComponent(new_component);
						viewer.refresh();
					}
				}
			}
		});
		viewer.setInput(new ArrayList());
		
//		IContextService contextService = (IContextService) getSite().getService(IContextService.class);
//		IContextActivation contextActivation = contextService.activateContext("jp.go.aist.rtm.repositoryView.context");
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				RepositoryView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		
		getSite().registerContextMenu(getSite().getId(), menuMgr, viewer);
		
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator("servers"));
		manager.add(new Separator("locals"));
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			return new RtcPropertySheetPage();
		}
		return super.getAdapter(adapter);
	}
	public Object getModel(){
		return viewer.getInput();
	}
}
