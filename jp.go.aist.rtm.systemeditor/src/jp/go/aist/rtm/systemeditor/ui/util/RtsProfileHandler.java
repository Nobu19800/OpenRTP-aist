package jp.go.aist.rtm.systemeditor.ui.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile.PROP;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;
import jp.go.aist.rtm.toolscommon.model.core.Point;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.ComponentExt;
import org.openrtp.namespaces.rts.version02.ConfigurationData;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;
import org.openrtp.namespaces.rts.version02.Dataport;
import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.DataportConnectorExt;
import org.openrtp.namespaces.rts.version02.DataportExt;
import org.openrtp.namespaces.rts.version02.ExecutionContext;
import org.openrtp.namespaces.rts.version02.ExecutionContextExt;
import org.openrtp.namespaces.rts.version02.Location;
import org.openrtp.namespaces.rts.version02.ObjectFactory;
import org.openrtp.namespaces.rts.version02.Participants;
import org.openrtp.namespaces.rts.version02.Property;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.Serviceport;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;
import org.openrtp.namespaces.rts.version02.ServiceportConnectorExt;
import org.openrtp.namespaces.rts.version02.ServiceportExt;
import org.openrtp.namespaces.rts.version02.TargetComponent;
import org.openrtp.namespaces.rts.version02.TargetComponentExt;
import org.openrtp.namespaces.rts.version02.TargetPort;
import org.openrtp.namespaces.rts.version02.TargetPortExt;

import RTC.RTObject;
import RTC.RTObjectHelper;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

import static jp.go.aist.rtm.toolscommon.model.component.Component.COMPOSITETYPE_GROUPING;
import static jp.go.aist.rtm.toolscommon.model.component.Component.COMPOSITETYPE_NONE;

import static jp.go.aist.rtm.toolscommon.model.component.ExecutionContext.KIND_PERIODIC;
import static jp.go.aist.rtm.toolscommon.model.component.ExecutionContext.KIND_EVENT_DRIVEN;
import static jp.go.aist.rtm.toolscommon.model.component.ExecutionContext.KIND_OTHER;
import static jp.go.aist.rtm.toolscommon.model.component.ExecutionContext.KIND_UNKNOWN;

/**
 * RTS�v���t�@�C���̓��o�͂��i��N���X
 * 
 */
public class RtsProfileHandler {
	
	private boolean online;
	private List<RepositoryViewItem> repositoryModel;
	private RtsProfileExt originalProfile;
	private List<String> savedConnectors;
	private ObjectFactory factory;
	private jp.go.aist.rtm.toolscommon.model.component.SystemDiagram diagram;

	static final String KEY_COMPONENT_PATH_ID = "COMPONENT_PATH_ID";
	static final String KEY_BEND_POINT = "BEND_POINT";
	static final String KEY_IOR = "IOR";

	/**
	 * RTS�v���t�@�C�������[�h����
	 * @param targetFile	���[�h�Ώۂ̃t�@�C��
	 * @param kind			�I�����C�����I�t���C�����̎��
	 * @return				���[�h�����V�X�e���_�C�A�O����
	 * @throws Exception
	 */
	public jp.go.aist.rtm.toolscommon.model.component.SystemDiagram load(
			String targetFile, SystemDiagramKind kind) throws Exception {
		XmlHandler handler = new XmlHandler();
		RtsProfileExt profile = handler.loadXmlRts(targetFile);
		return load(profile, kind);
	}

	public jp.go.aist.rtm.toolscommon.model.component.SystemDiagram load(
			RtsProfileExt profile, SystemDiagramKind kind) throws Exception {
		diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setProfile(profile);
		diagram.setKind(kind);
		setOnline(kind == SystemDiagramKind.ONLINE_LITERAL);
		populate(diagram, profile);
		return diagram;
	}

	/**
	 * �I�t���C���̃V�X�e���_�C�A�O���������[�h���邽�߂ɁARTC���|�W�g���̃��f�����Z�b�g����
	 * @param repositoryModel
	 */
	public void setRepositoryModel(List<RepositoryViewItem> repositoryModel) {
		this.repositoryModel = repositoryModel;
	}

	/**
	 * �_�C�A�O�����̒����Ɋ܂܂��S�R���|�[�l���g�ɑ΂��AIOR����CORABA�I�u�W�F�N�g��ݒ肷��
	 * @param eDiagram
	 */
	public void populateCorbaBaseObject(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		for (Object element : eDiagram.getRegisteredComponents()) {
			if (!(element instanceof jp.go.aist.rtm.toolscommon.model.component.CorbaComponent)) continue;
			jp.go.aist.rtm.toolscommon.model.component.CorbaComponent eCorbaComp = (jp.go.aist.rtm.toolscommon.model.component.CorbaComponent)element;
			String ior = eCorbaComp.getIor();
			if (ior == null) continue;
			eCorbaComp.setCorbaObject(getRTObject(ior));
		}
	}

	private RTObject getRTObject(String ior) {
		try {
			return RTObjectHelper.narrow(CorbaUtil.stringToObject(ior));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * RTS�v���t�@�C������_�C�A�O�������ɂ���R���|�[�l���g�Ԃ̐ڑ��𕜌�������
	 * @param eDiagram
	 */
	public void restoreConnection(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		this.diagram = eDiagram;
		setOnline(eDiagram.getKind() == SystemDiagramKind.ONLINE_LITERAL);
		RtsProfileExt profile = eDiagram.getProfile();
		List<jp.go.aist.rtm.toolscommon.model.component.Component> eComps
			= eDiagram.getRegisteredComponents();
		populateDataConnector(eComps, profile.getDataPortConnectors());
		populateServiceConnector(eComps, profile.getServicePortConnectors());
	}

	/**
	 * RTS�v���t�@�C������_�C�A�O�������ɂ���R���|�[�l���g�̃R���t�B�O�Z�b�g�𕜌�������
	 * @param eDiagram
	 */
	public void restoreConfigSet(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp : eDiagram
				.getRegisteredComponents()) {
			Component component = findComponent(eComp, eDiagram.getProfile()
					.getComponents());
			populateConfigSets(eComp, component);
		}
	}

	public void restoreConfigSetbyIOR(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp : eDiagram
				.getRegisteredComponents()) {
			Component component = findComponentByIOR(eComp, eDiagram
					.getProfile().getComponents());
			populateConfigSets(eComp, component);
		}
	}

	/**
	 * �I�t���C���̕����R���|�[�l���g�̃|�[�g�𕜌�������
	 * @param eDiagram
	 */
	public void restoreCompositeComponentPort(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		List<Component> source = eDiagram.getProfile().getComponents();
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp : eDiagram
				.getRegisteredComponents()) {
			Component component = findComponent(eComp, source);
			populateCompositeComponentPort(eComp, component);
		}
	}

    /**
	 * EC�𕜌�������
	 * 
	 * @param eDiagram
	 */
	public void restoreExecutionContext(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		List<Component> components = eDiagram.getProfile().getComponents();
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp : eDiagram
				.getRegisteredComponents()) {
			Component comp = findComponent(eComp, components);
			if (comp == null) {
				continue;
			}
			if (!online
					&& eComp instanceof jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification) {
				// �I�t���C���̏ꍇ�� owned EC���쐬
				// ���O�Ƀ��|�W�g������R�s�[���ꂽEC�̓N���A���ARTS�v���t�@�C���̂��̂�D��
				eComp.getExecutionContexts().clear();
				eComp.getExecutionContextHandler().clear();
				for (ExecutionContext ec : comp.getExecutionContexts()) {
					jp.go.aist.rtm.toolscommon.model.component.ExecutionContext eEc = ComponentFactory.eINSTANCE
							.createExecutionContext();
					if ("PERIODIC".equals(ec.getKind())) {
						eEc.setKindL(KIND_PERIODIC);
					} else if ("EVENT_DRIVEN".equals(ec.getKind())) {
						eEc.setKindL(KIND_EVENT_DRIVEN);
					} else if ("OTHER".equals(ec.getKind())) {
						eEc.setKindL(KIND_OTHER);
					} else {
						eEc.setKindL(KIND_UNKNOWN);
					}
					eEc.setRateR(ec.getRate());
					eEc.setOwner(eComp);
					eComp.getExecutionContexts().add(eEc);
				}
				eComp.getExecutionContextHandler().sync();
			} else {
				// �I�����C���̏ꍇ�� rate���X�V
				for (jp.go.aist.rtm.toolscommon.model.component.ExecutionContext eEc : eComp
						.getExecutionContexts()) {
					ExecutionContext ec = findExecutionContext(eEc, eComp, comp
							.getExecutionContexts());
					if (ec == null) {
						continue;
					}
					eEc.setRateR(ec.getRate());
				}
			}
			// EC��participant�̐ݒ�
			for (jp.go.aist.rtm.toolscommon.model.component.ExecutionContext eEc : eComp
					.getExecutionContexts()) {
				ExecutionContext ec = findExecutionContext(eEc, eComp, comp
						.getExecutionContexts());
				if (ec == null) {
					continue;
				}
				for (TargetComponent tc : ec.getParticipants()) {
					jp.go.aist.rtm.toolscommon.model.component.Component eComp2 = findEMFComponentByTargetComponent(
							tc, eDiagram.getRegisteredComponents());
					if (eComp2 == null) {
						continue;
					}
					if (!eComp2.getParticipationContexts().contains(eEc)) {
						eEc.addComponentR(eComp2);
					}
				}
			}
		}
	}

	/**
	 * RTS�v���t�@�C����ۑ�����
	 * 
	 * @param eDiagram
	 * @return
	 */
	public RtsProfileExt save(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		this.diagram = eDiagram;
		
		setOnline(eDiagram.getKind() == SystemDiagramKind.ONLINE_LITERAL);
		originalProfile = eDiagram.getProfile();
		savedConnectors = new ArrayList<String>();
		
		factory = new ObjectFactory();
		RtsProfileExt profile = factory.createRtsProfileExt();
		profile.setId(eDiagram.getSystemId());
		DatatypeFactory dateFactory = new DatatypeFactoryImpl();
		profile.setCreationDate(dateFactory.newXMLGregorianCalendar(eDiagram.getCreationDate()));
		profile.setUpdateDate(dateFactory.newXMLGregorianCalendar(eDiagram.getUpdateDate()));
		profile.setVersion("0.2");
		
		populateComponents(eDiagram, profile);
		
		populateFromProfileOnly(profile);
		return profile;
	}

	// �I�����C���G�f�B�^�ƃI�t���C���G�f�B�^�̂ǂ��炩��Ăяo���ꂽ����ݒ肷��
	void setOnline(boolean online) {
		this.online = online;
	}

	// Open����RTS�v���t�@�C�����V�X�e���_�C�A���O�ɕϊ�����
	void populate(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram,
			RtsProfileExt profile) {
		eDiagram.setSystemId(profile.getId());
		eDiagram.setCreationDate(profile.getCreationDate().toString());
		eDiagram.setUpdateDate(profile.getUpdateDate().toString());
		populate(eDiagram.getComponents(), profile.getComponents());
		// �|�[�g��CORBAObject�܂���RTCProfile�𐳂Ƃ���̂ŁA���̒i�K�ł͂܂��ڑ��ł��Ȃ�
	}

	// �x���h�|�C���g�̕�����\����Map�\���ɕϊ�����
	Map<Integer, Point> convertFromBendPointString(String bendPoint) {
		if (StringUtils.isBlank(bendPoint)) return null;
		String content = bendPoint.trim();
		content = content.substring(1, content.length() - 1).trim(); // { }����
		
		Map<Integer, Point> result = new HashMap<Integer, Point>();
		while(content.length() > 0) {
			content = populatePoint(result, content);
		}
		
		return result;
	}

	// Save���ɃV�X�e���_�C�A���O���Ɋ܂܂��R���|�[�l���g��RTS�v���t�@�C�����ɃZ�b�g����
	private void populateComponents(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram,
			RtsProfileExt rtsProfile) {
		List<Component> components = rtsProfile.getComponents();
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp:
				eDiagram.getRegisteredComponents()) {
			ComponentExt target = factory.createComponentExt();
			target.setId(eComp.getComponentId());
			target.setPathUri(eComp.getPathId());
			target.setInstanceName(eComp.getInstanceNameL());
			target.setCompositeType(eComp.getCompositeTypeL());
			target.setIsRequired(eComp.isRequired());
			
			Component original = findOriginalComponent(eComp);
			
			populateExecutionContext(eComp, target, original);			
			populateComponentLocation(eComp, target);			
			populateComponentProperty(eComp, target, original);
			populatePorts(eComp, target, original, rtsProfile);
			populateConfigurationSet(eComp, target);
			populateParticipants(eComp, target, original);
			
			populateFromProfileOnly(target, original);
			components.add(target);
		}
	}

	// Save���ɃV�X�e���_�C�A���O���Ɋ܂܂��f�[�^�|�[�g�Ƃ����̐ڑ���RTS�v���t�@�C�����ɃZ�b�g����
	// Save���Ƀ_�C�A�O�������Ɋ܂܂��T�[�r�X�|�[�g�i�Ƃ��̐ڑ��j�̏���RTS�v���t�@�C�����ɃZ�b�g����
	private void populatePorts(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			ComponentExt target, Component original, RtsProfileExt rtsProfile) {
		for (jp.go.aist.rtm.toolscommon.model.component.Port ePort : eComp.getOutports()) {
			addDataPort(ePort, target, original);
			for(jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf : ePort.getConnectorProfiles() ) {
				addDataPortConnector(eConnProf, rtsProfile, ePort);
			}
		}
		for (jp.go.aist.rtm.toolscommon.model.component.Port ePort : eComp.getInports()) {
			addDataPort(ePort, target, original);
		}
		for (jp.go.aist.rtm.toolscommon.model.component.Port ePort : eComp.getServiceports()) {
			addServicePort(ePort, target, original);
			for(jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf : ePort.getConnectorProfiles() ) {
				addServicePortConnector(eConnProf, rtsProfile, ePort);
			}
		}
	}

	// �f�[�^�|�[�g�R�l�N�^��RTS�ɒǉ�����
	private void addDataPortConnector(
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf,
			RtsProfileExt rtsProfile,
			jp.go.aist.rtm.toolscommon.model.component.Port ePort) {
		String connectorId = eConnProf.getConnectorId();
		if(savedConnectors.contains(connectorId) ) return;
		rtsProfile.getDataPortConnectors().add(saveDataPortConnector(ePort, eConnProf));
		savedConnectors.add(connectorId);		
	}

	// �T�[�r�X�|�[�g�R�l�N�^��RTS�ɒǉ�����
	private void addServicePortConnector(
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf,
			RtsProfileExt rtsProfile,
			jp.go.aist.rtm.toolscommon.model.component.Port ePort) {
		String connectorId = eConnProf.getConnectorId();
		if(savedConnectors.contains(connectorId) ) return;
		rtsProfile.getServicePortConnectors().add(saveServicePortConnector(ePort, eConnProf));
		savedConnectors.add(connectorId);		
	}

	// Save���ɃV�X�e���_�C�A���O���Ɋ܂܂��f�[�^�|�[�g�ڑ���RTS�v���t�@�C�����̊Y���v�f�ɕϊ�����
	private DataportConnector saveDataPortConnector(
			jp.go.aist.rtm.toolscommon.model.component.Port ePort,
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf) {
		DataportConnectorExt connector = factory.createDataportConnectorExt();
		connector.setConnectorId(eConnProf.getConnectorId());
		connector.setName(eConnProf.getName());
		connector.setInterfaceType(eConnProf.getInterfaceType());
		connector.setDataType(eConnProf.getDataType());
		connector.setDataflowType(eConnProf.getDataflowType());
		if(eConnProf.getSubscriptionType()!=null) connector.setSubscriptionType(eConnProf.getSubscriptionType());
		if(eConnProf.getPushRate()!=null) connector.setPushInterval(eConnProf.getPushRate());

		// �x���h�|�C���g�̕ۑ�
		jp.go.aist.rtm.toolscommon.model.component.PortConnector ePortConnector = diagram.getConnectorMap().get(eConnProf.getConnectorId());
		if (ePortConnector != null) {
			saveBendPoint(ePortConnector.getRoutingConstraint().map(), connector.getProperties());			
		}
		
		DataportConnector original = findOrignalDataportConnector(eConnProf.getConnectorId());

		connector.setSourceDataPort(createTargetPort(ePort.findPort(diagram, eConnProf.getSourceString())
				, original == null ? null : original.getSourceDataPort()));		
		connector.setTargetDataPort(createTargetPort(ePort.findPort(diagram, eConnProf.getTargetString())
				, original == null ? null : original.getTargetDataPort()));
		
		if (original instanceof DataportConnectorExt) {
			DataportConnectorExt originalExt = (DataportConnectorExt) original;
			connector.setComment(originalExt.getComment());
			if (!originalExt.isVisible()) {
				connector.setVisible(Boolean.valueOf(originalExt.isVisible()));
			}
			for (Property property : originalExt.getProperties()) {
				if (property.getName().equals(KEY_BEND_POINT)) continue;
				connector.getProperties().add(property);
			}
		}

		// �ǉ��v���p�e�B�̐ݒ�
		if (eConnProf.getPushPolicy() != null) {
			setProperty(PROP.PUSH_POLICY, eConnProf.getPushPolicy(), connector
					.getProperties());
		}
		if (eConnProf.getSkipCount() != null) {
			setProperty(PROP.SKIP_COUNT, eConnProf.getSkipCount().toString(),
					connector.getProperties());
		}
		//
		if (eConnProf.getOutportBufferLength() != null) {
			setProperty(PROP.OUTPORT_BUFF_LENGTH, eConnProf
					.getOutportBufferLength().toString(), connector
					.getProperties());
		}
		if (eConnProf.getOutportBufferFullPolicy() != null) {
			setProperty(PROP.OUTPORT_FULL_POLICY, eConnProf
					.getOutportBufferFullPolicy(), connector.getProperties());
		}
		if (eConnProf.getOutportBufferWriteTimeout() != null) {
			setProperty(PROP.OUTPORT_WRITE_TIMEOUT, eConnProf
					.getOutportBufferWriteTimeout().toString(), connector
					.getProperties());
		}
		if (eConnProf.getOutportBufferEmptyPolicy() != null) {
			setProperty(PROP.OUTPORT_EMPTY_POLICY, eConnProf
					.getOutportBufferEmptyPolicy(), connector.getProperties());
		}
		if (eConnProf.getOutportBufferReadTimeout() != null) {
			setProperty(PROP.OUTPORT_READ_TIMEOUT, eConnProf
					.getOutportBufferReadTimeout().toString(), connector
					.getProperties());
		}
		//
		if (eConnProf.getInportBufferLength() != null) {
			setProperty(PROP.INPORT_BUFF_LENGTH, eConnProf
					.getInportBufferLength().toString(), connector
					.getProperties());
		}
		if (eConnProf.getInportBufferFullPolicy() != null) {
			setProperty(PROP.INPORT_FULL_POLICY, eConnProf
					.getInportBufferFullPolicy(), connector.getProperties());
		}
		if (eConnProf.getInportBufferWriteTimeout() != null) {
			setProperty(PROP.INPORT_WRITE_TIMEOUT, eConnProf
					.getInportBufferWriteTimeout().toString(), connector
					.getProperties());
		}
		if (eConnProf.getInportBufferEmptyPolicy() != null) {
			setProperty(PROP.INPORT_EMPTY_POLICY, eConnProf
					.getInportBufferEmptyPolicy(), connector.getProperties());
		}
		if (eConnProf.getInportBufferReadTimeout() != null) {
			setProperty(PROP.INPORT_READ_TIMEOUT, eConnProf
					.getInportBufferReadTimeout().toString(), connector
					.getProperties());
		}

		return connector;
	}

	// Save���ɃV�X�e���_�C�A���O���Ɋ܂܂��T�[�r�X�|�[�g�ڑ���RTS�v���t�@�C�����̊Y���v�f�ɕϊ�����
	private ServiceportConnector saveServicePortConnector(
			jp.go.aist.rtm.toolscommon.model.component.Port ePort,
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf) {
		ServiceportConnectorExt connector = factory.createServiceportConnectorExt();
		connector.setConnectorId(eConnProf.getConnectorId());
		connector.setName(eConnProf.getName());
		// �x���h�|�C���g�̕ۑ�
		jp.go.aist.rtm.toolscommon.model.component.PortConnector ePortConnector = diagram.getConnectorMap().get(eConnProf.getConnectorId());
		if (ePortConnector != null) {
			saveBendPoint(ePortConnector.getRoutingConstraint().map(), connector.getProperties());			
		}

		ServiceportConnector original = findOrignalServiceportConnector(eConnProf.getConnectorId());

		connector.setSourceServicePort(createTargetPort(ePort.findPort(diagram, eConnProf.getSourceString())
				, original == null ? null : original.getSourceServicePort()));		
		connector.setTargetServicePort(createTargetPort(ePort.findPort(diagram, eConnProf.getTargetString())
				, original == null ? null : original.getTargetServicePort()));

		if (original instanceof ServiceportConnectorExt) {
			if (original != null) {
				connector.setTransMethod(original.getTransMethod());
			}
			ServiceportConnectorExt originalExt = (ServiceportConnectorExt) original;
			connector.setComment(originalExt.getComment());
			if (!originalExt.isVisible()) {
				connector.setVisible(Boolean.valueOf(originalExt.isVisible()));
			}
			for (Property property : originalExt.getProperties()) {
				if (property.getName().equals(KEY_BEND_POINT)) {
					continue;
				}
				if (jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile.InterfaceId.isValid(property.getName())) {
					// Consumer/Provider�̃v���p�e�B���X�L�b�v
					continue;
				}
				connector.getProperties().add(property);
			}
		}

		// �ǉ��v���p�e�B�̐ݒ�
		Property prop;
		for (String key : eConnProf.getPropertyKeys()) {
			if (jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile.InterfaceId.isValid(key)) {
				prop = newProperty(key, eConnProf.getProperty(key));
				connector.getProperties().add(prop);
			}
		}

		return connector;
	}

	// �x���h�|�C���g��RTS�v���t�@�C�����ɕۑ�����
	@SuppressWarnings("unchecked")
	private void saveBendPoint(Map map,
			List<Property> rtsProperties) {
		if (map == null || map.isEmpty()) return;
		
		Property propt = factory.createProperty();
		propt.setName(KEY_BEND_POINT);
		propt.setValue(convertToBendPointString(map));
		rtsProperties.add(propt);
	}

	// �x���h�|�C���g��Map�𕶎���\���ɕϊ�����
	@SuppressWarnings("unchecked")
	private String convertToBendPointString(Map map) {
		StringBuffer buffer = new StringBuffer();
		for (Object key :map.keySet()) {
			if (buffer.length() == 0) {
				buffer.append("{");
			} else {
				buffer.append(",");
			}
			buffer.append(key).append(":").append("(");
			jp.go.aist.rtm.toolscommon.model.core.Point point = (Point) map.get(key);
			buffer.append(point.getX()).append(",").append(point.getY());
			buffer.append(")");
		}
		buffer.append("}");
		return buffer.toString();
	}

	// �v���t�@�C����TargetPort�𐶐����ĕԂ�
	private TargetPort createTargetPort(
			jp.go.aist.rtm.toolscommon.model.component.Port ePort,
			TargetPort original) {
		TargetPortExt port = factory.createTargetPortExt();
		final jp.go.aist.rtm.toolscommon.model.component.Component eComp = (jp.go.aist.rtm.toolscommon.model.component.Component) ePort.eContainer();
		port.setComponentId(eComp.getComponentId());
		port.setInstanceName(eComp.getInstanceNameL());
		port.setPortName(ePort.getNameL());

		// pathId���v���p�e�B�ɃZ�b�g����
		Property propt = factory.createProperty();
		propt.setName(KEY_COMPONENT_PATH_ID);
		propt.setValue(eComp.getPathId());
		port.getProperties().add(propt);

		if (original instanceof TargetPortExt) {
			TargetPortExt originalPort = (TargetPortExt) original;
			for (Property property : originalPort.getProperties()) {
				if (property.getName().equals(KEY_COMPONENT_PATH_ID)) continue;
				port.getProperties().add(property);
			}
		}
		return port;
	}

	// Save����Component��ConfigurationSet�̏���RTS�v���t�@�C���ɃZ�b�g����
	private void populateConfigurationSet(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp, ComponentExt target) {
		for (jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet eConfigSet : eComp.getConfigurationSets()) {
			ConfigurationSet config = factory.createConfigurationSet();
			config.setId(eConfigSet.getId());
			for (jp.go.aist.rtm.toolscommon.model.component.NameValue nv : eConfigSet
					.getConfigurationData()) {
				ConfigurationData data = factory.createConfigurationData();
				data.setName(nv.getName());
				data.setData(nv.getValueAsString());
				config.getConfigurationData().add(data);
			}
			target.getConfigurationSets().add(config);
			if (eConfigSet.equals(eComp.getActiveConfigurationSet())) {
				target.setActiveConfigurationSet(eConfigSet.getId());
			}
		}
	}

	// Save���ɎqRTC�̏���RTS�v���t�@�C���ɃZ�b�g����
	private void populateParticipants(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			ComponentExt target, Component original) {
		for (jp.go.aist.rtm.toolscommon.model.component.Component eChildComp : eComp
				.getComponents()) {
			Participants participants = null;
			if (original != null) {
				participants = findParticipants(eChildComp, original.getParticipants());
			}
			if (participants != null) {
				target.getParticipants().add(participants);
				continue;
			}
			TargetComponentExt child = factory.createTargetComponentExt();
			child.setComponentId(eChildComp.getComponentId());
			child.setInstanceName(eChildComp.getInstanceNameL());

			// pathId���v���p�e�B�ɃZ�b�g����
			Property propt = factory.createProperty();
			propt.setName(KEY_COMPONENT_PATH_ID);
			propt.setValue(eChildComp.getPathId());
			child.getProperties().add(propt);

			participants = factory.createParticipants();
			participants.setParticipant(child);
			target.getParticipants().add(participants);
		}
	}

	// Save���Ƀf�[�^�|�[�g�̏���RTS�v���t�@�C���ɒǉ�����
	private void addDataPort(
			jp.go.aist.rtm.toolscommon.model.component.Port ePort,
			ComponentExt target, Component original) {
		DataportExt port = factory.createDataportExt();
		port.setName(ePort.getNameL());
		if (original != null) {
			Dataport originalPort = findOriginalPort(original.getDataPorts(), port.getName());
			if (originalPort instanceof DataportExt) {
				DataportExt source = (DataportExt) originalPort;
				port.setComment(source.getComment());
				if (!source.isVisible()) {
					port.setVisible(Boolean.valueOf(source.isVisible()));
				}
				port.getProperties().addAll(source.getProperties());
			}
		}
		target.getDataPorts().add(port);
	}

	// Save���ɃT�[�r�X�|�[�g�̏���RTS�v���t�@�C���ɒǉ�����
	private void addServicePort(
			jp.go.aist.rtm.toolscommon.model.component.Port ePort,
			ComponentExt target, Component original) {
		ServiceportExt port = factory.createServiceportExt();
		port.setName(ePort.getNameL());
		if (original != null) {
			Serviceport originalPort = findOriginalPort(original.getServicePorts(), port.getName());
			if (originalPort instanceof ServiceportExt) {
				ServiceportExt source = (ServiceportExt) originalPort;
				port.setComment(source.getComment());
				if (!source.isVisible()) {
					port.setVisible(Boolean.valueOf(source.isVisible()));
				}
				port.getProperties().addAll(source.getProperties());
			}
		}
		target.getServicePorts().add(port);
	}

	// Save���ɃR���|�[�l���g�̃v���p�e�B���Z�b�g����
	private void populateComponentProperty(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			ComponentExt target, Component original) {
		List<Property> rtsProperties = target.getProperties();
		if (original instanceof ComponentExt) {
			for(Property prop :  ((ComponentExt)original).getProperties()) {
				if (!prop.getName().equals(KEY_IOR)) rtsProperties.add(prop);
			}			
		}
		populateIOR(rtsProperties, eComp);
	}

	// Save���ɃR���|�[�l���g�̈ʒu�����Z�b�g����
	private void populateComponentLocation(jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			ComponentExt target) {
		target.setLocation(new Location());
		target.getLocation().setX(BigInteger.valueOf(eComp.getConstraint().getX()));
		target.getLocation().setY(BigInteger.valueOf(eComp.getConstraint().getY()));
		target.getLocation().setHeight(BigInteger.valueOf(eComp.getConstraint().getHeight()));
		target.getLocation().setWidth(BigInteger.valueOf(eComp.getConstraint().getWidth()));
		target.getLocation().setDirection(eComp.getOutportDirection());
	}
	
	// IOR��ۑ�����
	private void populateIOR(List<Property> rtsProperties,
			jp.go.aist.rtm.toolscommon.model.component.Component eComp) {
		if (!(eComp instanceof jp.go.aist.rtm.toolscommon.model.component.CorbaComponent)) return;
		RTObject corbaObjectInterface = ((jp.go.aist.rtm.toolscommon.model.component.CorbaComponent) eComp).getCorbaObjectInterface();
		if (corbaObjectInterface == null) return;
		rtsProperties.add(newProperty(KEY_IOR, corbaObjectInterface.toString()));
	}

	// IOR�𕜌�����
	private void populateIOR(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			List<Property> properties) {
		if (!(eComp instanceof jp.go.aist.rtm.toolscommon.model.component.CorbaComponent)) return;
		for (Property prop : properties) {
			if (prop.getName().equals(KEY_IOR)) {
				((jp.go.aist.rtm.toolscommon.model.component.CorbaComponent) eComp).setIor(prop.getValue());
			}
		}
	}

	// Save���Ɍ��̃t�@�C���ɂ������R���|�[�l���g�̊g���������Z�b�g����
	private void populateFromProfileOnly(ComponentExt target, Component original) {
		if (!(original instanceof ComponentExt)) return;
		ComponentExt source = (ComponentExt) original;
		target.setComment(source.getComment());
		if (!source.isVisible()) {
			target.setVisible(Boolean.valueOf(source.isVisible()));
		}
	}

	// Save����ExecutionContext�̏���RTS�v���t�@�C���ɃZ�b�g����
	private void populateExecutionContext(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			ComponentExt target, Component original) {
		for (jp.go.aist.rtm.toolscommon.model.component.ExecutionContext eEc : eComp
				.getExecutionContexts()) {
			String id = eComp.getExecutionContextHandler().getId(eEc);
			ExecutionContextExt ec = factory.createExecutionContextExt();
			ec.setId((id == null) ? "" : id);
			ec.setKind(eEc.getKindName());
			ec.setRate(eEc.getRateL());
			for (jp.go.aist.rtm.toolscommon.model.component.Component c : eEc
					.getParticipants()) {
				TargetComponentExt tc = factory.createTargetComponentExt();
				tc.setComponentId(c.getComponentId());
				tc.setInstanceName(c.getInstanceNameL());
				Property prop = newProperty(KEY_COMPONENT_PATH_ID, c.getPathId());
				tc.getProperties().add(prop);
				ec.getParticipants().add(tc);
			}
			for (jp.go.aist.rtm.toolscommon.model.component.NameValue nv : eEc
					.getProperties()) {
				setProperty(nv.getName(), nv.getValueAsString(), ec
						.getProperties());
			}
			target.getExecutionContexts().add(ec);
		}
	}

	// Save���ɁA����RTS�v���t�@�C���������ɑ��݂��A�V�X�e���G�f�B�^�ł͎g�p���Ȃ��v�f�������߂�
	private void populateFromProfileOnly(RtsProfileExt target) {
		if (originalProfile == null) return;
		target.setAbstract(originalProfile.getAbstract());
		target.getGroups().addAll(originalProfile.getGroups());
		target.getProperties().addAll(originalProfile.getProperties());
		target.setStartUp(originalProfile.getStartUp());
		target.setShutDown(originalProfile.getShutDown());
		target.setActivation(originalProfile.getActivation());
		target.setDeactivation(originalProfile.getDeactivation());
		target.setResetting(originalProfile.getResetting());
		target.setInitializing(originalProfile.getInitializing());
		target.setFinalizing(originalProfile.getFinalizing());
		target.setComment(originalProfile.getComment());
		target.getVersionUpLogs().addAll(originalProfile.getVersionUpLogs());
	}

	// �x���h�|�C���g�̕�����\�������Map�\���̃G���g����1���o���āA�Z�b�g����
	private String populatePoint(Map<Integer, Point> result, String content) {
		String key = content.substring(0, content.indexOf(":"));
		String value = content.substring(content.indexOf(":") + 1).trim();
		String x = value.substring(1, value.indexOf(",")).trim();
		value = value.substring(value.indexOf(",") + 1).trim();
		String y = value.substring(0, value.indexOf(")")).trim();

		Point point = new Point();
		point.setX(Integer.parseInt(x));
		point.setY(Integer.parseInt(y));
		result.put(new Integer(key), point);

		if (value.indexOf(",") < 0) return "";
		return value.substring(value.indexOf(",") + 1).trim();
	}

	// RTS�v���t�@�C������EMF�R���|�[�l���g�𕜌�����
	private void populate(
			EList<jp.go.aist.rtm.toolscommon.model.component.Component> target,
			List<Component> components) {
		List<jp.go.aist.rtm.toolscommon.model.component.Component> eComps = new ArrayList<jp.go.aist.rtm.toolscommon.model.component.Component>();
		for (Component component : components) {
			jp.go.aist.rtm.toolscommon.model.component.Component eComp = createEMFComponent(component);
			eComp.setComponentId(component.getId());
			eComp.setPathId(component.getPathUri());
			eComp.setInstanceNameL(component.getInstanceName());
			populateCompositeType(eComp, component.getCompositeType());
			eComp.setRequired(component.isIsRequired());

			// port��CORBAObject�܂���RTEProfile�𐳂Ƃ���
			// CORBA�o�R�Ŏ擾�����R���t�B�O�Z�b�g�𐳂Ƃ���

			if (eComp instanceof jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification) {
				// �I�t���C���̏ꍇ�̓|�[�g���𐳋K������
				jp.go.aist.rtm.toolscommon.model.component.Component spec = (jp.go.aist.rtm.toolscommon.model.component.Component) eComp;
				for (jp.go.aist.rtm.toolscommon.model.component.Port port : spec
						.getPorts()) {
					String name = ComponentUtil.createPortName(spec
							.getInstanceNameL(), port.getNameL());
					port.setNameL(name);
				}
			}

			if (online && eComp.getCompositeTypeL().equals(COMPOSITETYPE_GROUPING)) {
				// Grouping����RTC�̏ꍇ�́AConfigurationSet�𕜌�����
				populateConfigSets(eComp, component);
			}

			if (component instanceof ComponentExt) {
				ComponentExt componentExt = (ComponentExt)component;
				eComp.setConstraint(toRectangle(componentExt.getLocation()));
				eComp.setOutportDirection(componentExt.getLocation().getDirection());
				populateIOR(eComp, componentExt.getProperties());
			}
			eComps.add(eComp);
		}
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp : eComps) {
			if (isShown(eComp, eComps, components))
				target.add(eComp);
		}
	}

	// ���[�g�̃V�X�e���_�C�A�O�����ɕ\�������̂ł���΁Atrue��Ԃ�
	private boolean isShown(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			List<jp.go.aist.rtm.toolscommon.model.component.Component> eComps,
			List<Component> source) {
		// targetComponent��parentComponent�̎q�Ƃ��Ēǉ����鏈���������ōs��
		for (int i = 0; i < eComps.size(); i++) {
			jp.go.aist.rtm.toolscommon.model.component.Component eParentComponent 
				= eComps.get(i);
			Component component = source.get(i);
			for (Participants participants : component.getParticipants()) {
				TargetComponent tc = participants.getParticipant();
				if (eComp.getComponentId().equals(tc.getComponentId())
						&& eComp.getInstanceNameL().equals(tc.getInstanceName())) {
					// pathId���`�F�b�N����
					if (equalsPathId(eComp, tc)) {
						eParentComponent.getComponents().add(eComp);
						return false;
					}	
				}
			}
		}
		return true;
	}

	// Rts�v���t�@�C����Location��Rectangle�ɕϊ�����
	private Rectangle toRectangle(Location location) {
		Rectangle result = new Rectangle();
		result.setX(location.getX().intValue());
		result.setY(location.getY().intValue());
		result.setWidth(location.getWidth().intValue());
		result.setHeight(location.getHeight().intValue());
		return result;
	}

	// �����R���|�[�l���g�̃|�[�g�𕜌�������i�I�t���C���j
	private void populateCompositeComponentPort(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			Component component) {
		if (!eComp.isCompositeComponent()) return;
		if (!online || eComp.getCompositeTypeL().equals(COMPOSITETYPE_GROUPING)) {
				List<jp.go.aist.rtm.toolscommon.model.component.Component> emptyList 
					= Collections.emptyList();
				eComp.addComponentsR(emptyList);
		}
	}

	// �f�[�^�|�[�g�̐ڑ��𕜌�������
	private void populateDataConnector(
			List<jp.go.aist.rtm.toolscommon.model.component.Component> eComps,
			List<DataportConnector> dataPortConnectors) {
		for (DataportConnector connBase : dataPortConnectors) {
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile conn = ComponentFactory.eINSTANCE.createConnectorProfile();
			conn.setConnectorId(connBase.getConnectorId());
			conn.setName(connBase.getName());
			conn.setInterfaceType(connBase.getInterfaceType());
			conn.setDataType(connBase.getDataType());
			conn.setDataflowType(connBase.getDataflowType());
			if(connBase.getSubscriptionType()!=null) conn.setSubscriptionType(connBase.getSubscriptionType());
			if(connBase.getPushInterval()!=null) conn.setPushRate(connBase.getPushInterval());

			// �ǉ��v���p�e�B�̐ݒ�
			if (connBase instanceof DataportConnectorExt) {
				DataportConnectorExt connExt = (DataportConnectorExt) connBase;
				for (Property p : connExt.getProperties()) {
					String name = p.getName();
					String value = p.getValue();
					if (PROP.PUSH_POLICY.equals(name)) {
						conn.setPushPolicy(value);
					}
					if (PROP.SKIP_COUNT.equals(name)) {
						try {
							int i = Integer.parseInt(value);
							conn.setSkipCount(i);
						} catch (Exception e) {
							// void
						}
					}
					//
					if (PROP.OUTPORT_BUFF_LENGTH.equals(name)) {
						try {
							int i = Integer.parseInt(value);
							conn.setOutportBufferLength(i);
						} catch (Exception e) {
							// void
						}
					}
					if (PROP.OUTPORT_FULL_POLICY.equals(name)) {
						conn.setOutportBufferFullPolicy(value);
					}
					if (PROP.OUTPORT_WRITE_TIMEOUT.equals(name)) {
						try {
							double d = Double.parseDouble(value);
							conn.setOutportBufferWriteTimeout(d);
						} catch (Exception e) {
							// void
						}
					}
					if (PROP.OUTPORT_EMPTY_POLICY.equals(name)) {
						conn.setOutportBufferEmptyPolicy(value);
					}
					if (PROP.OUTPORT_READ_TIMEOUT.equals(name)) {
						try {
							double d = Double.parseDouble(value);
							conn.setOutportBufferReadTimeout(d);
						} catch (Exception e) {
							// void
						}
					}
					//
					if (PROP.INPORT_BUFF_LENGTH.equals(name)) {
						try {
							int i = Integer.parseInt(value);
							conn.setInportBufferLength(i);
						} catch (Exception e) {
							// void
						}
					}
					if (PROP.INPORT_FULL_POLICY.equals(name)) {
						conn.setInportBufferFullPolicy(value);
					}
					if (PROP.INPORT_WRITE_TIMEOUT.equals(name)) {
						try {
							double d = Double.parseDouble(value);
							conn.setInportBufferWriteTimeout(d);
						} catch (Exception e) {
							// void
						}
					}
					if (PROP.INPORT_EMPTY_POLICY.equals(name)) {
						conn.setInportBufferEmptyPolicy(value);
					}
					if (PROP.INPORT_READ_TIMEOUT.equals(name)) {
						try {
							double d = Double.parseDouble(value);
							conn.setInportBufferReadTimeout(d);
						} catch (Exception e) {
							// void
						}
					}
				}
			}

			connectPorts(conn, eComps,
					connBase.getTargetDataPort(), connBase.getSourceDataPort()
					, getBendPoint(connBase));
		}
	}

	// RTS�v���t�@�C���̃T�[�r�X�|�[�g�ڑ�����ڑ��𕜌�����
	private void populateServiceConnector(
			List<jp.go.aist.rtm.toolscommon.model.component.Component> eComp,
			List<ServiceportConnector> servicePortConnectors) {
		for (ServiceportConnector connBase : servicePortConnectors) {
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf = ComponentFactory.eINSTANCE.createConnectorProfile();
			eConnProf.setConnectorId(connBase.getConnectorId());
			eConnProf.setName(connBase.getName());

			// �ǉ��v���p�e�B�̐ݒ�
			if (connBase instanceof ServiceportConnectorExt) {
				ServiceportConnectorExt connExt = (ServiceportConnectorExt) connBase;
				for (Property p : connExt.getProperties()) {
					String name = p.getName();
					String value = p.getValue();
					if (jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile.InterfaceId.isValid(name)) {
						eConnProf.setProperty(name, value);
					}
				}
			}

			connectPorts(eConnProf, eComp, connBase.getTargetServicePort(),
					connBase.getSourceServicePort(), getBendPoint(connBase));
		}
	}

	// �x���h�|�C���g���v���p�e�B���畜������
	private Map<Integer, Point> getBendPoint(DataportConnector connBase) {
		if (!(connBase instanceof DataportConnectorExt)) return null;
		DataportConnectorExt connBaseExt = (DataportConnectorExt) connBase;
		return getBendPoint(connBaseExt.getProperties());
	}

	// �x���h�|�C���g���v���p�e�B���畜������
	private Map<Integer, Point> getBendPoint(ServiceportConnector connBase) {
		if (!(connBase instanceof ServiceportConnectorExt)) return null;
		ServiceportConnectorExt connBaseExt = (ServiceportConnectorExt) connBase;
		return getBendPoint(connBaseExt.getProperties());
	}

	// �x���h�|�C���g���v���p�e�B���畜������
	private Map<Integer, Point> getBendPoint(List<Property> properties) {
		String bendPointString = findProperyValue(KEY_BEND_POINT, properties);
		if (bendPointString == null) return null;
		return convertFromBendPointString(bendPointString);
	}

	// �|�[�g�Ԃ̐ڑ��𕜌�����
	private void connectPorts(
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile eConnProf,
			List<jp.go.aist.rtm.toolscommon.model.component.Component> eComps,
			TargetPort target, TargetPort source, Map<Integer, Point> bendPoint) {
		jp.go.aist.rtm.toolscommon.model.component.PortConnector eConnector = PortConnectorFactory.createPortConnector(online);
		if (!online) {
			diagram.getConnectorMap().put(eConnProf.getConnectorId(), eConnector);
		}
		jp.go.aist.rtm.toolscommon.model.component.Component eComp = null;
		eComp = findEMFComponentByTargetComponent(source, eComps);
		if (eComp != null) {
			eConnector.setSource(findEMFPortByTargetPort(source, eComp
					.getPorts()));
		}
		eComp = findEMFComponentByTargetComponent(target, eComps);
		if (eComp != null) {
			eConnector.setTarget(findEMFPortByTargetPort(target, eComp
					.getPorts()));
		}
		eConnector.setConnectorProfile(eConnProf);
		if (bendPoint != null && !bendPoint.isEmpty()){
			eConnector.getRoutingConstraint().map().clear();
			eConnector.getRoutingConstraint().map().putAll(bendPoint);
		}
		eConnector.createConnectorR();
	}

	// RTS�v���t�@�C������R���|�W�b�g��ʂ𕜌�����
	private void populateCompositeType(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			String compositeType) {
		if (compositeType.equals("None")) return;
		eComp.setCategoryL("composite." + compositeType);
	}

	// RTS�v���t�@�C������R���t�B�O�Z�b�g�𕜌�����
	private void populateConfigSets(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			Component component) {
		eComp.getConfigurationSets().clear();
		String activeId = component.getActiveConfigurationSet();
		for (ConfigurationSet configSet : component.getConfigurationSets()) {
			jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet eConfigSet = ComponentFactory.eINSTANCE.createConfigurationSet();
			if (configSet.getId().equals(activeId)) eComp.setActiveConfigurationSet(eConfigSet);
			eConfigSet.setId(configSet.getId());
			for (ConfigurationData configData : configSet.getConfigurationData()) {
				jp.go.aist.rtm.toolscommon.model.component.NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
				nv.setName(configData.getName());
				nv.setValue(configData.getData());
				eConfigSet.getConfigurationData().add(nv);
			}
			eComp.getConfigurationSets().add(eConfigSet);
		}
	}

	/** �L�[�ƒl���w�肵�ăv���p�e�B���X�g���X�V(�L�[�Ɉ�v����v�f������Βl���㏑��) */
	void setProperty(String name, String value, List<Property> properties) {
		Property prop = findProperty(name, properties);
		if (prop != null) {
			prop.setValue(value);
		} else {
			prop = newProperty(name, value);
			properties.add(prop);
		}
	}

	/** �L�[�ƒl���w�肵�ăv���p�e�B�v�f���쐬 */
	Property newProperty(String name, String value) {
		Property prop = factory.createProperty();
		prop.setName(name);
		prop.setValue(value);
		return prop;
	}

	/** TargetComponent��PathId�������AEMF�R���|�[�l���g��PathId���������ꍇ��true */
	private boolean equalsPathId(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			TargetComponent tc) {
		String pathId = getPathId(tc);
		if (pathId == null) {
			return true;
		}
		return pathId.equals(eComp.getPathId());
	}

	/** TargetComponent�A��������TargetPort�̃v���p�e�B����PathId�����o�� */
	private String getPathId(TargetComponent tc) {
		if (tc instanceof TargetPortExt) {
			TargetPortExt saved = (TargetPortExt) tc;
			return findProperyValue(KEY_COMPONENT_PATH_ID, saved.getProperties());
		}
		if (tc instanceof TargetComponentExt) {
			TargetComponentExt saved = (TargetComponentExt) tc;
			return findProperyValue(KEY_COMPONENT_PATH_ID, saved.getProperties());
		}
		return null;
	}

	/** EMF�R���|�[�l���g���쐬���� */
	private jp.go.aist.rtm.toolscommon.model.component.Component createEMFComponent(
			Component component) {
		if (online) {
			if (component.getCompositeType().equals(COMPOSITETYPE_GROUPING)) {
				return ComponentFactory.eINSTANCE
						.createComponentSpecification();
			}
			// TODO:CORBA�ȊO�ւ̑Ή�
			return ComponentFactory.eINSTANCE.createCorbaComponent();
		} else if (component.getCompositeType().equals(COMPOSITETYPE_NONE)) {
			String componentid = component.getId();
			String pathId = component.getPathUri();
			jp.go.aist.rtm.toolscommon.model.component.Component spec = findEMFComponentOnRepository(
					componentid, pathId, repositoryModel);
			if (spec == null) {
				throw new IllegalStateException("Target Component["
						+ componentid + "](" + pathId
						+ ") does not exist in RepositoryView.");
			}
			return spec.copy();
		} else {
			return ComponentFactory.eINSTANCE.createComponentSpecification();
		}
	}

	/** Open���ɓǂݍ���RTS�v���t�@�C������Y������f�[�^�|�[�g�ڑ��������o�� */
	private DataportConnector findOrignalDataportConnector(String connectorId) {
		if (originalProfile == null) {
			return null;
		}
		for (DataportConnector temp : originalProfile.getDataPortConnectors()) {
			if (temp.getConnectorId().equals(connectorId)) {
				return temp;
			}
		}
		return null;
	}

	/** Open���ɓǂݍ���RTS�v���t�@�C������Y������T�[�r�X�|�[�g�ڑ��������o�� */
	private ServiceportConnector findOrignalServiceportConnector(
			String connectorId) {
		if (originalProfile == null) {
			return null;
		}
		for (ServiceportConnector temp : originalProfile
				.getServicePortConnectors()) {
			if (temp.getConnectorId().equals(connectorId)) {
				return temp;
			}
		}
		return null;
	}

	/** Open����RTS�v���t�@�C���ɑ��݂���Dataport�v�f��T���o�� */
	private Dataport findOriginalPort(List<Dataport> ports, String name) {
		for (Dataport port : ports) {
			if (port.getName().equals(name)) {
				return port;
			}
		}
		return null;
	}

	/** Open����RTS�v���t�@�C���ɑ��݂���Serviceport�v�f��T���o�� */
	private Serviceport findOriginalPort(List<Serviceport> ports, String name) {
		for (Serviceport port : ports) {
			if (port.getName().equals(name)) {
				return port;
			}
		}
		return null;
	}

	/** Open����RTS�v���t�@�C���ɑ��݂���Component�v�f��T���o�� */
	private Component findOriginalComponent(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp) {
		if (originalProfile == null) {
			return null;
		}
		return findComponent(eComp, originalProfile.getComponents());
	}

	/** EMF�R���|�[�l���g�ɍ��v����RTS�R���|�[�l���g��T���o�� */
	public static Component findComponent(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			List<Component> components) {
		for (Component component : components) {
			if (component.getId().equals(eComp.getComponentId())
					&& component.getInstanceName().equals(
							eComp.getInstanceNameL())
					&& component.getPathUri().equals(eComp.getPathId())) {
				return component;
			}
		}
		return null;
	}

	/** EMF�R���|�[�l���g��IOR�ɍ��v����RTS�R���|�[�l���g��T���o�� */
	public static Component findComponentByIOR(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			List<Component> components) {
		if (!(eComp instanceof jp.go.aist.rtm.toolscommon.model.component.CorbaComponent)) {
			return null;
		}
		jp.go.aist.rtm.toolscommon.model.component.CorbaComponent eCorbaComp = (jp.go.aist.rtm.toolscommon.model.component.CorbaComponent) eComp;
		for (Component c : components) {
			if (!(c instanceof ComponentExt)) {
				continue;
			}
			ComponentExt cx = (ComponentExt) c;
			String compIor = findProperyValue(KEY_IOR, cx.getProperties());
			if (compIor == null) {
				continue;
			}
			if (compIor.equals(eCorbaComp.getIor())) {
				return cx;
			}
		}
		return null;
	}

	/** EMF�R���|�[�l���g�ɍ��v����Participants�v�f��T���o�� */
	private Participants findParticipants(
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			List<Participants> participants) {
		for (Participants participant : participants) {
			TargetComponent tc = participant.getParticipant();
			if (tc.getComponentId().equals(eComp.getComponentId())
					&& tc.getInstanceName().equals(eComp.getInstanceNameL())) {
				// pathId���`�F�b�N����
				if (equalsPathId(eComp, tc)) {
					return participant;
				}
			}
		}
		return null;
	}

	/** EMF�R���|�[�l���g��EC�ɍ��v����EC��T���o�� */
	public static ExecutionContext findExecutionContext(
			jp.go.aist.rtm.toolscommon.model.component.ExecutionContext eEc,
			jp.go.aist.rtm.toolscommon.model.component.Component eComp,
			List<ExecutionContext> contexts) {
		String id = eComp.getExecutionContextHandler().getId(eEc);
		if (id == null) {
			return null;
		}
		for (ExecutionContext ec : contexts) {
			if (id.equals(ec.getId()) && eEc.getKindName().equals(ec.getKind())) {
				return ec;
			}
		}
		return null;
	}

	/** TargetComponent�ɍ��v����EMF�R���|�[�l���g��T���o�� */
	private jp.go.aist.rtm.toolscommon.model.component.Component findEMFComponentByTargetComponent(
			TargetComponent tc,
			List<jp.go.aist.rtm.toolscommon.model.component.Component> eComps) {
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp : eComps) {
			if (eComp.getComponentId().equals(tc.getComponentId())
					&& eComp.getInstanceNameL().equals(tc.getInstanceName())) {
				// pathId���`�F�b�N����
				if (equalsPathId(eComp, tc)) {
					return eComp;
				}
			}
		}
		return null;
	}

	/** TargetPort�ɍ��v����EMF�|�[�g��T���o�� */
	private jp.go.aist.rtm.toolscommon.model.component.Port findEMFPortByTargetPort(
			TargetPort tp,
			List<jp.go.aist.rtm.toolscommon.model.component.Port> ePorts) {
		String name = tp.getPortName();
		if (!name.startsWith(tp.getInstanceName())) {
			// �I�t���C���̏ꍇ�̓|�[�g���𐳋K������
			name = ComponentUtil.createPortName(tp.getInstanceName(), tp
					.getPortName());
		}
		for (jp.go.aist.rtm.toolscommon.model.component.Port ePort : ePorts) {
			if (name.equals(ePort.getNameL())) {
				return ePort;
			}
		}
		return null;
	}

	/** ���|�W�g������EMF�R���|�[�l���g��T���o���i�I�t���C���j */
	private jp.go.aist.rtm.toolscommon.model.component.Component findEMFComponentOnRepository(
			String id, String pathId, List<RepositoryViewItem> models) {
		if (models == null) {
			return null;
		}
		for (RepositoryViewItem item : models) {
			if (item instanceof RepositoryViewLeafItem) {
				jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification target = ((RepositoryViewLeafItem) item)
						.getComponent();
				if (target == null) {
					return null;
				}
				if (target.getComponentId().equals(id)
						&& target.getPathId().equals(pathId)) {
					return target;
				}
			} else {
				jp.go.aist.rtm.toolscommon.model.component.Component result = findEMFComponentOnRepository(
						id, pathId, item.getChildren());
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	/** RTS�v���t�@�C���̃v���p�e�B�̃��X�g����A�w�肵���L�[�̃v���p�e�B��T���o�� */
	static Property findProperty(String name, List<Property> properties) {
		if (name == null) {
			return null;
		}
		for (Property p : properties) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}

	/** RTS�v���t�@�C���̃v���p�e�B�̃��X�g����A�w�肵���L�[�̒l��T���o�� */
	static String findProperyValue(String name, List<Property> properties) {
		Property p = findProperty(name, properties);
		return (p != null) ? p.getValue() : null;
	}

}
