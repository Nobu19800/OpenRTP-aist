package jp.go.aist.rtm.systemeditor.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.action.OpenCompositeComponentAction;
import jp.go.aist.rtm.systemeditor.ui.dialog.ProfileInformationDialog;
import jp.go.aist.rtm.systemeditor.ui.editor.action.ChangeComponentDirectionAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.MoveComponentAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.OpenAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.RestoreOption;
import jp.go.aist.rtm.systemeditor.ui.editor.dnd.SystemDiagramDropTargetListener;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.AutoScrollAutoexposeHelper;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.factory.SystemDiagramEditPartFactory;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import jp.go.aist.rtm.toolscommon.ui.views.propertysheetview.RtcPropertySheetPage;
import jp.go.aist.rtm.toolscommon.validation.ValidateException;
import jp.go.aist.rtm.toolscommon.validation.Validator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

public abstract class AbstractSystemDiagramEditor extends GraphicalEditor {

	static final String EXTENTION_POINT_NAME = "postsaveextension";
	private static List<PostSaveExtension> postSaveList;
	
	private static void buildExtensionDataSave() {
		postSaveList = new ArrayList<PostSaveExtension>();
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
			.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof PostSaveExtension) {
						postSaveList.add((PostSaveExtension)obj);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	
	public static final String MSG_TITLE_VALIDATION_ERROR = Messages.getString("AbstractSystemDiagramEditor.33");

	/**
	 * �V�X�e���_�C�A�O�����̊g���q
	 */
	public static final String FILE_EXTENTION = "xml"; //$NON-NLS-1$

	/**
	 * RTCLink�̃v���W�F�N�g��
	 */
	public static String RTCLINK_PROJECT_NAME = "RTSE_Files"; //$NON-NLS-1$

	private String title = null;

	private SystemDiagram systemDiagram;

	private SystemDiagramPropertyChangeListener systemDiagramPropertyChangeListener;

	protected String diagramName;

	public AbstractSystemDiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
		getEditDomain().setCommandStack(new ConnectCancelCommandStack());
	}
	
	/**
	 * @return openEditor�̈����Ƃ��ēn�����i�I�����C���܂��̓I�t���C���́j�G�f�B�^ID
	 */
	abstract public String getEditorId();

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createActions() {
		super.createActions();

		IAction action;

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_UP_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_DOWN_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_RIGHT_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_LEFT_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_UP_L_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_DOWN_L_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_RIGHT_L_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new MoveComponentAction(this,
				MoveComponentAction.MOVE_LEFT_L_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new ChangeComponentDirectionAction(this,
				ChangeComponentDirectionAction.HORIZON_DIRECTION_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new ChangeComponentDirectionAction(this,
				ChangeComponentDirectionAction.VERTICAL_DIRECTION_ACTION_ID);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new OpenCompositeComponentAction(this);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new SaveAction(this) {
			@Override
			protected boolean calculateEnabled() {
				return true;
			}

			@Override
			protected void init() {
				setId(ActionFactory.SAVE_AS.getId());
				setText(Messages.getString("AbstractSystemDiagramEditor.3")); //$NON-NLS-1$
				setToolTipText(Messages.getString("AbstractSystemDiagramEditor.4")); //$NON-NLS-1$
			}

			public void run() {
				doSaveAs();
			}
		};
		getActionRegistry().registerAction(action);
		getPropertyActions().add(action.getId());

		action = new OpenAction(this);
		getActionRegistry().registerAction(action);
		getPropertyActions().add(action.getId());

	}

	@Override
	protected void createGraphicalViewer(Composite parent) {
		GraphicalViewer viewer = new ScrollingGraphicalViewer() {

			@SuppressWarnings("unchecked")
			@Override
			public EditPart findObjectAtExcluding(Point pt, Collection exclude,
					Conditional condition) {
				EditPart result = super.findObjectAtExcluding(pt, exclude,
						condition);
				if (condition instanceof AutoexposeHelper.Search
						&& ((AutoexposeHelper.Search) condition).result == null) {
					((AutoexposeHelper.Search) condition).result = (AutoexposeHelper) getRootEditPart()
							.getAdapter(AutoexposeHelper.class);
				}
				return result;
			}
		};
		viewer.createControl(parent);
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setRootEditPart(new ScalableRootEditPart() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getAdapter(Class adapter) {
				if (adapter == AutoexposeHelper.class) {
					return new AutoScrollAutoexposeHelper(this);
				}

				return super.getAdapter(adapter);
			}
			@Override
			public DragTracker getDragTracker(Request req) {
				return new MarqueeDragTracker(){

					@Override
					protected boolean handleButtonDown(int button) {
						boolean handleButtonDown = super.handleButtonDown(button);
						if (button == 3) { // right click
							deselectAll();
						}
						return handleButtonDown;
					}
					
				};
			}
		});
		viewer.setEditPartFactory(new SystemDiagramEditPartFactory(
				getActionRegistry()));
	}


	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		viewer
				.addDropTargetListener((TransferDropTargetListener) new SystemDiagramDropTargetListener(
						viewer));

		ContextMenuProvider provider = new AbstractSystemDiagramContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		((IEditorSite) getSite()).registerContextMenu(provider, viewer, false);

		buildKeyHandler(viewer);

		systemDiagramPropertyChangeListener = new SystemDiagramPropertyChangeListener(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage());
		systemDiagram
				.addPropertyChangeListener(systemDiagramPropertyChangeListener);
		viewer.setContents(systemDiagram);
	}

	private void buildKeyHandler(GraphicalViewer viewer) {
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_UP, 0),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_UP_ACTION_ID));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_DOWN, 0),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_DOWN_ACTION_ID));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_RIGHT, 0),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_RIGHT_ACTION_ID));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_LEFT, 0),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_LEFT_ACTION_ID));

		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_UP, SWT.SHIFT),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_UP_L_ACTION_ID));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_DOWN, SWT.SHIFT),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_DOWN_L_ACTION_ID));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_RIGHT, SWT.SHIFT),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_RIGHT_L_ACTION_ID));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_LEFT, SWT.SHIFT),
				getActionRegistry().getAction(
						MoveComponentAction.MOVE_LEFT_L_ACTION_ID));

		viewer.setKeyHandler(keyHandler);
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void doSave(IProgressMonitor monitor) {
		IEditorInput input = getInput();
		
		if (input instanceof NullEditorInput) { // �V�K�G�f�B�^
			doSaveAs();
			return;
		}
		
		// �I�����C���ł�����_�C�A���O���o���悤�ɂ��Ă��� 2008.12.11
		ProfileInformationDialog dialog = new ProfileInformationDialog(getSite().getShell());
		dialog.setSystemId(getSystemDiagram().getSystemId());
		dialog.setOverWrite(true);
		dialog.setComponets(getSystemDiagram().getComponents());
		if( dialog.open() != IDialogConstants.OK_ID ) return;
		
		String systemId = Messages.getString("AbstractSystemDiagramEditor.9") + dialog.getInputVendor() + ":"  //$NON-NLS-1$ //$NON-NLS-2$
							+ dialog.getInputSystemName() + ":" //$NON-NLS-1$
							+ dialog.getInputVersion();
		getSystemDiagram().setSystemId(systemId);
		DatatypeFactory dateFactory = new DatatypeFactoryImpl();
		getSystemDiagram().setUpdateDate(dateFactory.newXMLGregorianCalendar(new GregorianCalendar()).toString());

		// �_�C�A�O�����̃o���f�[�V����
		if (!validateDiagram()) {
			return;
		}

		try {
			IFile file = ((IFileEditorInput) input).getFile();
			save(file, monitor);
			saveExtensionData(file, monitor);
		} catch (CoreException e) {
			e.printStackTrace();
			ErrorDialog.openError(getSite().getShell(), Messages.getString("AbstractSystemDiagramEditor.12"), //$NON-NLS-1$
					Messages.getString("AbstractSystemDiagramEditor.13"), e.getStatus()); //$NON-NLS-1$
		}
	}

	private IEditorInput getInput() {
		IEditorInput input = getEditorInput();
		SystemDiagram diagram = systemDiagram.getParentSystemDiagram();
		while (diagram != null) {
			AbstractSystemDiagramEditor editor = ComponentUtil.findEditor(diagram);
			input = editor.getEditorInput();
			diagram = diagram.getParentSystemDiagram();
		}
		return input;
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void doSaveAs() {
		ProfileInformationDialog dialog = new ProfileInformationDialog(getSite().getShell());
		if( getSystemDiagram().getSystemId() != null ) {
			dialog.setSystemId(getSystemDiagram().getSystemId());
		}
		dialog.setComponets(getSystemDiagram().getComponents());
		
		if( dialog.open() != IDialogConstants.OK_ID ) return;
		
		String systemId = Messages.getString("AbstractSystemDiagramEditor.18") + dialog.getInputVendor() + ":"  //$NON-NLS-1$ //$NON-NLS-2$
							+ dialog.getInputSystemName() + ":" //$NON-NLS-1$
							+ dialog.getInputVersion();
		getSystemDiagram().setSystemId(systemId);
		DatatypeFactory dateFactory = new DatatypeFactoryImpl();
		getSystemDiagram().setCreationDate(dateFactory.newXMLGregorianCalendar(new GregorianCalendar()).toString());
		getSystemDiagram().setUpdateDate(dateFactory.newXMLGregorianCalendar(new GregorianCalendar()).toString());

		// �_�C�A�O�����̃o���f�[�V����
		if (!validateDiagram()) {
			return;
		}

		IPath result = new Path(dialog.getInputPath());
		final IFile newFile = createNewFile(result);
		if (newFile == null)
			return;

		ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(
				getSite().getShell());
		try {
			progressMonitorDialog.run(false, false,
				new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						try {
							save(newFile, monitor);
							saveExtensionData(newFile, monitor);
						} catch (CoreException e) {
							e.printStackTrace();
							MessageDialog.openError(getSite().getShell(), Messages.getString("AbstractSystemDiagramEditor.21"), //$NON-NLS-1$
									Messages.getString("AbstractSystemDiagramEditor.22") + newFile.getName()); //$NON-NLS-1$
						}
					}
				});
		} catch (Exception e) {
			throw new RuntimeException(e); // SystemError
		}
	}
	
	private void saveExtensionData(IFile file,IProgressMonitor progressMonitor) {
		if(postSaveList == null) {
			buildExtensionDataSave();
		}
		for(PostSaveExtension extension : postSaveList) {
			PostSaveExtension.ResultCode result =
				extension.execute(this, file, progressMonitor);
			if(result == PostSaveExtension.ResultCode.FAILURE) {
				extension.showErrorMessage(getSite().getShell());
			}
		}
	}
	/**
	 * �_�C�A�O�����̐��������`�F�b�N���܂��B
	 * 
	 * �g���|�C���g: jp.go.aist.rtm.toolscommon.validations
	 * 
	 * @return �s�����̏ꍇ��false
	 */
	public boolean validateDiagram() {
		try {
			Validator.validate(systemDiagram.getRootDiagram());
		} catch (ValidateException e) {
			MessageDialog.openError(getSite().getShell(),
					MSG_TITLE_VALIDATION_ERROR, e.getMessage() + "\n"
							+ e.getDetail());
			return false;
		}
		return true;
	}

	private IFile createNewFilebySelection(IFile oldFile, int style) {
		final IPath newPath = getFilePathByDialog(oldFile, style);
		return createNewFile(newPath);
	}

	private IFile createNewFile(IPath newPath) {
		if (newPath == null) return null;

		if (newPath.toFile().exists() == false) {
			try {
				newPath.toFile().createNewFile();
			} catch (IOException e) {
				MessageDialog.openError(getSite().getShell(), Messages.getString("AbstractSystemDiagramEditor.23"), //$NON-NLS-1$
						Messages.getString("AbstractSystemDiagramEditor.24") + newPath.toOSString()); //$NON-NLS-1$
			}
		}

		if (newPath.toFile().exists()) {
			return getOutsideIFileLink(newPath);
		}
		return null;
	}

	private IPath getFilePathByDialog(IFile defaultFile, int style) {
		FileDialog dialog = new FileDialog(getSite().getShell(), style);

		if (defaultFile != null) {
			dialog.setFileName(defaultFile.toString());
		}
		dialog.setFilterExtensions(new String[] { "*." + FILE_EXTENTION }); //$NON-NLS-1$

		String pathString = dialog.open();

		if (pathString == null) {
			return null;
		}

		IPath result = new Path(pathString);
		if (result.getFileExtension() == null) {
			result = result.addFileExtension(FILE_EXTENTION);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected void save(IFile file, IProgressMonitor progressMonitor)
			throws CoreException {
		
		List<AbstractSystemDiagramEditor> editors = new ArrayList<AbstractSystemDiagramEditor>();
		editors.add(this);
		
		// �q�G�f�B�^���擾
		for (Iterator iterator = systemDiagram.getComponents().iterator(); iterator
				.hasNext();) {
			Component compnent = (Component) iterator.next();
			if (compnent.isCompositeComponent()
					&& compnent.getChildSystemDiagram() != null) {
				AbstractSystemDiagramEditor editor = ComponentUtil.findEditor(compnent.getChildSystemDiagram());
				if (editor != null)
					editors.add(editor);
			}
		}
		// �e�G�f�B�^���擾
		SystemDiagram parentSystemDiagram = systemDiagram.getParentSystemDiagram();
		while (parentSystemDiagram != null) {
			AbstractSystemDiagramEditor editor = ComponentUtil.findEditor(parentSystemDiagram);
			if (editor != null)
				editors.add(editor);

			parentSystemDiagram.setSystemId(systemDiagram.getSystemId());
			parentSystemDiagram.setCreationDate(systemDiagram.getCreationDate());
			parentSystemDiagram.setUpdateDate(systemDiagram.getUpdateDate());
			for (Iterator iterator = systemDiagram.getComponents()
					.iterator(); iterator.hasNext();) {
				Component component = (Component) iterator
						.next();
				Component localObject = getLocalObject(parentSystemDiagram,
						component);

				// Constraint��ύX
				if (localObject != null) {
					localObject.getConstraint().setX(
							component.getConstraint().getX());
					localObject.getConstraint().setY(
							component.getConstraint().getY());
					localObject.getConstraint().setHeight(
							component.getConstraint().getHeight());
					localObject.getConstraint().setWidth(
							component.getConstraint().getWidth());
				}
			}

			parentSystemDiagram = parentSystemDiagram.getParentSystemDiagram();
		}
		if (null == progressMonitor)
			progressMonitor = new NullProgressMonitor();

		progressMonitor.beginTask(Messages.getString("AbstractSystemDiagramEditor.26") + file.getLocation().toOSString(), //$NON-NLS-1$
				2);

		try {
			progressMonitor.worked(1);

			Resource resource = null;
			ResourceSet resourceSet = new ResourceSetImpl();
			resource = resourceSet.createResource(URI
					.createFileURI(file.getLocation().toOSString()));

			SystemEditorWrapperFactory.getInstance().saveContentsToResource(
					resource, systemDiagram.getRootDiagram());

			file.refreshLocal(IResource.DEPTH_ZERO, null);
			for (AbstractSystemDiagramEditor editor : editors) {
				editor.changeFile(file);
			}
			progressMonitor.worked(2);
			progressMonitor.done();
		} catch (FileNotFoundException e) {
			progressMonitor.done();
			IStatus status = new Status(IStatus.ERROR, RTSystemEditorPlugin.getDefault()
					.getClass().getName(), 0, Messages.getString("AbstractSystemDiagramEditor.27"), e); //$NON-NLS-1$
			throw new CoreException(status);

		} catch (Exception e) {
			progressMonitor.done();
			IStatus status = new Status(IStatus.ERROR, RTSystemEditorPlugin.getDefault()
					.getClass().getName(), 0, Messages.getString("AbstractSystemDiagramEditor.28"), e); //$NON-NLS-1$
			throw new CoreException(status);
		}
	}

	private Component getLocalObject(SystemDiagram systemDiagram,
			Component component) {
		if (component instanceof CorbaComponent) {
			org.omg.CORBA.Object obj = ((CorbaComponent)component).getCorbaObject();
			if (obj != null) {
				return (Component) SynchronizationSupport
				.findLocalObjectByRemoteObject(
						new Object[] { obj },
						systemDiagram);				
			}
		}
		return findComponentByPathId(
				component, systemDiagram);
	}

	/**
	 * component��diagram����T���iPathId�ŒT��)
	 * @param component
	 * @param diagram
	 * @return
	 */
	private Component findComponentByPathId(
			Component component, SystemDiagram diagram) {
		if (component == null || diagram == null) {
			return null;
		}
		for (Component tempComponent : diagram.getRegisteredComponents()) {
			if (tempComponent.getPathId() != null
					&& tempComponent.getPathId().equals(component.getPathId())) {
				return tempComponent;
			}
		}
		return null;
	}

	public void changeFile(IFile file) {
		if (this.systemDiagram.getParentSystemDiagram() != null) {
			title = systemDiagram.getCompositeComponent().getInstanceNameL();
		} else {
			setInput(new FileEditorInput(file));
			title = file.getLocation().lastSegment();
		}
		getCommandStack().markSaveLocation();
		firePropertyChange(IEditorPart.PROP_TITLE);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean isDirty() {
		return getCommandStack().isDirty();
	}

	// ConfigSet���ύX���ꂽ�̂ŁA�����I��dirty�Ƃ���
	public void setDirty() {
		Command cannnotUndoCommand = new Command(){
			@Override
			public boolean canUndo() {
				return false;
			}};
		getCommandStack().execute(cannnotUndoCommand);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		IEditorInput newInput;
		try {
			newInput = load(input, site, RestoreOption.NONE);
		} catch (Throwable t) {
			// �N�����Ƀt�@�C���I�[�v���G���[�������������̓G�f�B�^�̒��g����ɂ��� 2009.11.06
			newInput = load(new NullEditorInput(), site, RestoreOption.NONE);
		}
		super.init(site, newInput);
	}

	protected abstract IEditorInput load(IEditorInput input,
			final IEditorSite site, final RestoreOption doReplace)
			throws PartInitException;

	private SimpleDateFormat formater = new SimpleDateFormat(
			Messages.getString("AbstractSystemDiagramEditor.29")); //$NON-NLS-1$

	protected IEditorInput getTargetInput(IEditorInput input, String windowName) {
		if (input instanceof NullEditorInput) {
			setSystemDiagram(ComponentFactory.eINSTANCE.createSystemDiagram());
			diagramName = windowName;
			setInput(input);
			return input;
		} else  {
			FileEditorInput editorInput = createEditorInput(input);
			diagramName = editorInput.getPath().lastSegment();
			setInput(editorInput);
			return editorInput;
		}
	}

	protected FileEditorInput createEditorInput(IEditorInput input) {
		if (input instanceof FileEditorInput) return (FileEditorInput) input;
		
		IPath path = ((IPathEditorInput) input).getPath();
		IFile file = getOutsideIFileLink(path);
		return new FileEditorInput(file);
	}

	public void postLoad() {
		GraphicalViewer graphicalViewer2 = getGraphicalViewer();
		if (graphicalViewer2 != null) { // �������[�h�̏ꍇ�ɂ͑��݂��Ȃ��B�ʓr��Ń��[�h����
			graphicalViewer2.setContents(getSystemDiagram());
		}

		getCommandStack().markSaveLocation();
		firePropertyChange(IEditorPart.PROP_TITLE);
	}

	protected IFile getOutsideIFileLink(IPath path) {
		IProject project = ensureProjectOpen();

		IFile fileLink = getFileLinkByRawLocation(path, project);
		
		if (fileLink != null) return fileLink;
		

		fileLink = project.getFile(formater.format(new Date()) + "__" //$NON-NLS-1$
				+ path.lastSegment());
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e1) {
			throw new RuntimeException(e1); // systemError
		}
		if (fileLink.exists() == false) {
			try {
				fileLink.createLink(path, IResource.NONE, null);
			} catch (CoreException e) {
				throw new RuntimeException(e); // systemError
			}
		}

		return fileLink;
	}

	private IFile getFileLinkByRawLocation(IPath path, IProject project) {
		try {
			for (IResource r : project.members()) {
				if (r.getType() == IResource.FILE && r.isLinked()
						&& path.equals(r.getRawLocation())) {
					return (IFile) r;
				}
			}
		} catch (CoreException e2) {
			// void
		}
		return null;
	}

	private IProject ensureProjectOpen() {
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IProject project = ws.getRoot().getProject(RTCLINK_PROJECT_NAME);
		if (!project.exists())
			try {
				project.create(null);
			} catch (CoreException e) {
				throw new RuntimeException(e); // systemError
			}
		if (!project.isOpen())
			try {
				project.open(null);
			} catch (CoreException e) {
				throw new RuntimeException(e); // systemError
			}
		return project;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		try {
			super.dispose();
		} catch (NullPointerException e) {
			// do nothing
		}

		if (getSystemDiagramPropertyChangeListener() != null) {
			getSystemDiagramPropertyChangeListener().dispose();
		}

		// ����RTC�G�f�B�^���J����Ă��������
		if (getSystemDiagram() == null) return;
		if (getSystemDiagram().getComponents() == null) return;
		for (Object o : getSystemDiagram().getComponents()) {
			Component ac = (Component) o;
			ComponentUtil.closeCompositeComponent(ac);
		}
		systemDiagram = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(Class type) {
		if (type.equals(IPropertySheetPage.class)) {
			return new RtcPropertySheetPage();
		}

		return super.getAdapter(type);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public String getTitle() {
		return (title == null) ? diagramName : title;
	}

	public void open(RestoreOption restore) {
		boolean save = false;
		if (isDirty()) {
			save = MessageDialog.openQuestion(getSite().getShell(), "", //$NON-NLS-1$
					Messages.getString("AbstractSystemDiagramEditor.32")); //$NON-NLS-1$
		}

		if (save) {
			doSave(null);
		}

		title = null;
		
		IFile createNewFile = createNewFilebySelection(null, SWT.OPEN);
		if (createNewFile != null) {
			try {
				load(new FileEditorInput(createNewFile), getEditorSite(),
						restore);
			} catch (PartInitException e) {
				e.printStackTrace(); // system error
				if (e.getStatus().getException() != null)
					MessageDialog.openError(getSite().getShell(), "", e //$NON-NLS-1$
							.getStatus().getException().getMessage());
			}
		}
	}

	/**
	 * SystemDiagram���擾����
	 * 
	 * @return
	 */
	public SystemDiagram getSystemDiagram() {
		return systemDiagram;
	}

	/**
	 * 
	 */
	protected final class SystemDiagramPropertyChangeListener implements
			PropertyChangeListener {
		private IWorkbenchPage page;

		private SystemDiagramPropertyChangeListener(IWorkbenchPage page) {
			super();
			this.page = page;
		}

		/**
		 * {@inheritDoc}
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals("SYSTEM_DIAGRAM_COMPONENTS")) { //$NON-NLS-1$
				final PropertyChangeEvent changeEvent = evt;
				if (evt.getOldValue() instanceof Component
						&& ((Component) evt.getOldValue())
								.isCompositeComponent()) {
					try {
						page.getWorkbenchWindow().getShell().getDisplay()
								.asyncExec(new Runnable() {
									@SuppressWarnings("unchecked")
									public void run() {
										List<Component> components = new ArrayList<Component>();
										components
												.add((Component) changeEvent
														.getOldValue());
										components
												.addAll(((Component) changeEvent
														.getOldValue())
														.getAllComponents());
										for (Component tmpComponent : components) {
											SystemDiagram childDiagram = tmpComponent.getChildSystemDiagram();
											if (childDiagram != null) {
												AbstractSystemDiagramEditor editor = ComponentUtil.findEditor(childDiagram);
												if (editor != null)	page.closeEditor(editor, false);
												tmpComponent.setChildSystemDiagram(null);
											}
										}
									}
								});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		public void dispose() {
			if (systemDiagram != null) {
				systemDiagram.removePropertyChangeListener(this);
			}
		}
	}

	abstract public boolean isOnline();

	public SystemDiagramPropertyChangeListener getSystemDiagramPropertyChangeListener() {
		return systemDiagramPropertyChangeListener;
	}

	public void setSystemDiagram(SystemDiagram systemDiagram) {
		this.systemDiagram = systemDiagram;
	}
	public void deselectAll() {
		getGraphicalViewer().deselectAll();
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		if (getSystemDiagram().getParentSystemDiagram() != null) {
			// ����RTC�̃G�f�B�^�̏ꍇ�͕ۑ����Ȃ�
			return false;
		}
		return super.isSaveOnCloseNeeded();
	}

	public EditPart findEditPart(Object model) {
		if (model == null)
			return null;
		EditPart part = getGraphicalViewer().getContents();
		for (Object o : part.getChildren()) {
			EditPart child = (EditPart) o;
			if (model.equals(child.getModel())) {
				return child;
			}
		}
		return null;
	}

	/**
	 * �G�f�B�^���̃R���|�[�l���g���ĕ`�悷��
	 */
	public void refresh() {
		for (Object model : getSystemDiagram().getComponents()) {
			EditPart ep = findEditPart(model);
			if (ep != null) {
				for (Object obj :ep.getChildren()) {
					((EditPart)obj).refresh();
				}
			}
		}
		AbstractSystemDiagramEditor parent = ComponentUtil.findEditor(getSystemDiagram().getParentSystemDiagram());
		if (parent != null) parent.refresh();
		
	}
}
