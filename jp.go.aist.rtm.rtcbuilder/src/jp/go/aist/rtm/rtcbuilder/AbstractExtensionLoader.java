package jp.go.aist.rtm.rtcbuilder;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public abstract class AbstractExtensionLoader {
	// Manager
	private List list = new ArrayList();
	
	abstract String getPointId();
	abstract String getExtensionName();
	abstract void addExtension(IConfigurationElement element) throws CoreException;
	
	/**
	 * �g���I�u�W�F�N�g�����[�h���ă��X�g�Ɋi�[����B
	 * @throws CoreException 
	 */
	public void loadExtensions() throws CoreException {
		// �g���|�C���g�̎擾
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint( getPointId() );		
		if( point == null ) return;		
		
		// �g���錾�̃��[�h
		IExtension[] extensions = point.getExtensions();
		for( int index = 0; index < extensions.length; index++ ) {
			// �g���錾�iextension�^�O�j���ƂɁA���ʂ̃^�O����������
			IConfigurationElement[] cfgElems = extensions[index].getConfigurationElements();
			for(int intext = 0; intext < cfgElems.length; intext++) {
				IConfigurationElement cfgElem = cfgElems[intext];
				
				if ( getExtensionName().equals( cfgElem.getName() ) ) {
					processManager( cfgElem );
				}
			}
		}
	}

	protected void processManager(IConfigurationElement cfgElem) throws CoreException {
		try {
			if ( cfgElem.isValid() ) {
//				Object obj = cfgElem.createExecutableExtension( "managerclass" );
//				if ( obj instanceof GenerateManager ) {
//					GenerateManager manager = (GenerateManager)obj;
//					list.add(manager);
//				}
				addExtension(cfgElem);
			}
		} catch (CoreException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List getList() {
		return list;
	}
}
