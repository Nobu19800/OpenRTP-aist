package jp.go.aist.rtm.systemeditor.ui.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.restoration.Restoration;
import jp.go.aist.rtm.systemeditor.restoration.Result;
import jp.go.aist.rtm.systemeditor.ui.editor.action.OpenAndRestoreAction;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

/**
 * SystemDiagramEditor�N���X
 */
public class SystemDiagramEditor extends AbstractSystemDiagramEditor {

	/**
	 * �V�X�e���_�C�A�O�����G�f�B�^��ID
	 */
	public static final String SYSTEM_DIAGRAM_EDITOR_ID = "jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor";

	private String diagramName = "System Diagram";

	/**
	 * �R���X�g���N�^
	 */
	public SystemDiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createActions() {
		super.createActions();

		IAction action;

		action = new OpenAndRestoreAction(this);
		getActionRegistry().registerAction(action);
		getPropertyActions().add(action.getId());
	}

	/**
	 * �ݒ�̕ύX�ɑ΂��郊�X�i
	 */
	PropertyChangeListener preferenceListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			getSystemDiagram()
					.setSynchronizeInterval(
							SystemEditorPreferenceManager
									.getInstance()
									.getInterval(
											SystemEditorPreferenceManager.SYNC_SYSTEMEDITOR_INTERVAL));
		}
	};

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void initializeGraphicalViewer() {

		super.initializeGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();

		ContextMenuProvider provider = new SystemDiagramContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		((IEditorSite) getSite()).registerContextMenu(provider, viewer, false);

		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(
				preferenceListener);

		getSystemDiagram().setEditorId(getEditorId());
		viewer.setContents(getSystemDiagram());
	}

	protected IEditorInput load(IEditorInput input, final IEditorSite site,
			final boolean doReplace) throws PartInitException {
		boolean newOpenEditor = input instanceof NullEditorInput;// �V�K�G�f�B�^

		IEditorInput result = input;
		if (newOpenEditor) {
			// void
		} else if (!(input instanceof FileEditorInput)) {
			IPath path = ((IPathEditorInput) input).getPath();
			IFile file = getOutsideIFileLink(path);
			result = new FileEditorInput(file);
		}

		if (newOpenEditor) {
			setSystemDiagram(ComponentFactory.eINSTANCE.createSystemDiagram());
		} else if (result instanceof FileEditorInput) {
			try {
				ResourceSet resourceSet = new ResourceSetImpl();

				final Resource resource = resourceSet.createResource(URI
						.createFileURI(((FileEditorInput) result).getPath()
								.toOSString()));
				if (getSystemDiagram() != null) {
					getSystemDiagram().setSynchronizeInterval(0);
				}

				ProgressMonitorDialog dialog = new ProgressMonitorDialog(site
						.getShell());
				IRunnableWithProgress runable = new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {

						monitor.beginTask("�t�@�C���̃I�[�v�����s���Ă��܂�", 100);

						monitor
								.subTask("�t�@�C�����I�[�v�����A�K�v�ɉ����ăc���[����ŐV���̎擾���s���Ă��܂�...");
						monitor.internalWorked(20);

						try {
							SystemDiagram diagram = (SystemDiagram) SystemEditorWrapperFactory
														.getInstance().loadContentFromResource(resource);
							for (Object object : diagram.getComponents()) {
								if (!(object instanceof Component)) {
									throw new InvocationTargetException(new Exception(),
									"�t�@�C���̓ǂݍ��݂Ɏ��s���܂����B\r\nSystem Diagram�ȊO�̃t�@�C�����ǂݍ��܂�Ă��Ȃ����m�F���Ă��������B");
								}
							}
							setSystemDiagram(diagram);
						} catch (IOException e) {
							throw new InvocationTargetException(e,
									"�t�@�C���̓ǂݍ��݂Ɏ��s���܂����B\r\nSystem Diagram�ȊO�̃t�@�C�����ǂݍ��܂�Ă��Ȃ����m�F���Ă��������B");
						}
						monitor.internalWorked(35);

						if (doReplace) {
							monitor.subTask("�V�X�e���̕������s���Ă��܂�...");
							doReplace(getSystemDiagram(), site);
						}
						monitor.done();
					}
				};

				dialog.run(false, false, runable);
			} catch (Exception e) {
				throw new PartInitException("�I�[�v���Ɏ��s���܂����B", e);
			}
		}

		getSystemDiagram()
				.setSynchronizeInterval(
						SystemEditorPreferenceManager
								.getInstance()
								.getInterval(
										SystemEditorPreferenceManager.SYNC_SYSTEMEDITOR_INTERVAL));

		if (newOpenEditor) {
			diagramName = "System Diagram";
		} else if (result instanceof FileEditorInput) {
			diagramName = ((FileEditorInput) result).getPath().lastSegment();
		}

		getCommandStack().markSaveLocation(); // load�����n�j
		firePropertyChange(IEditorPart.PROP_TITLE);

		this.setInput(result);

		GraphicalViewer graphicalViewer2 = getGraphicalViewer();
		if (graphicalViewer2 != null) { // �������[�h�̏ꍇ�ɂ͑��݂��Ȃ��B�ʓr��Ń��[�h����
			graphicalViewer2.setContents(getSystemDiagram());
		}

		getCommandStack().markSaveLocation();
		firePropertyChange(IEditorPart.PROP_TITLE);
		getSystemDiagram().setEditorId(SYSTEM_DIAGRAM_EDITOR_ID);
		return result;
	}

	/**
	 * ���[�h���̕������s���܂��B
	 */
	private void doReplace(SystemDiagram systemDiagram, IEditorSite site) {
		final StringBuffer buffer = new StringBuffer();
		Result resultHolder = new Result() {
			private boolean success;

			public boolean isSuccess() {
				return success;
			}

			public void setSuccess(boolean success) {
				this.success = success;
			}

			public void putResult(String resultPart) {
				buffer.append(resultPart + "\r\n");
			}
		};
		Restoration.execute(systemDiagram, resultHolder);
		if (resultHolder.isSuccess() == false) {
			Dialog dialog = new jp.go.aist.rtm.toolscommon.ui.dialog.ErrorDialog(
					site.getShell(), "�G���[", null, "�����Ɏ��s���܂����B\r\n", buffer
							.toString(), MessageDialog.ERROR);
			dialog.open();
		}
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		super.dispose();

		SystemEditorPreferenceManager.getInstance()
				.removePropertyChangeListener(preferenceListener);

		getSystemDiagram().setSynchronizeInterval(-1);
	}

	@Override
	public String getEditorId() {
		return SYSTEM_DIAGRAM_EDITOR_ID;
	}

	@Override
	protected String getDiagramName() {

		return diagramName;
	}
}
