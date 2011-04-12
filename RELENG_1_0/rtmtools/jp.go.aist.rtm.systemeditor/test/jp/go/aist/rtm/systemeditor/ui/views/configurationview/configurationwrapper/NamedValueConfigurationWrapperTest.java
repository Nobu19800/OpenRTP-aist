package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.Map;

import junit.framework.TestCase;

public class NamedValueConfigurationWrapperTest extends TestCase {

	public void testSetWidgetAndCondition() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key");
		// ��������Ȃ�
		nv.setWidgetAndCondition("text", null);
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// slider�͍ŏ��A�ő�ݒ肪�K�v
		nv.setWidgetAndCondition("slider", "0<x<100");
		assertEquals(true, nv.widget().isSlider());
		assertEquals("0", nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("slider", "x<100");
		assertEquals(false, nv.widget().isSlider());
		assertEquals(true, nv.widget().isText());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("slider", "");
		assertEquals(false, nv.widget().isSlider());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// spinner�͍ő�A�ŏ��ݒ肪�K�v
		nv.setWidgetAndCondition("spin", "0<x<100");
		assertEquals(true, nv.widget().isSpinner());
		assertEquals("0", nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("spin", "x<100");
		assertEquals(false, nv.widget().isSpinner());
		assertEquals(true, nv.widget().isText());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("spin", "");
		assertEquals(false, nv.widget().isSpinner());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// radio�͗񋓐ݒ肪�K�v
		nv.setWidgetAndCondition("radio", "(100,200,300)");
		assertEquals(true, nv.widget().isRadio());
		assertEquals(true, nv.widget().getCondition().hasEnumList());
		assertEquals(3, nv.widget().getCondition().getEnumList().size());

		nv.setWidgetAndCondition("radio", "x<100");
		assertEquals(false, nv.widget().isRadio());
		assertEquals(true, nv.widget().isText());
		assertEquals(false, nv.widget().getCondition().hasEnumList());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("radio", "");
		assertEquals(false, nv.widget().isRadio());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// ��������G���[�̏ꍇ��text
		nv.setWidgetAndCondition("radio", "(100,200"); // �񋓕����ʂȂ�
		assertEquals(false, nv.widget().isRadio());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		nv.setWidgetAndCondition("slider", "0<x<100a"); // ���l���
		assertEquals(false, nv.widget().isSlider());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());


		// �z��\�L
		nv.setWidgetAndCondition("slider", "0<x<1, 1<x<2");
		assertEquals(2, nv.widgetSize());
		assertEquals(true, nv.widget(0).isSlider());
		assertEquals("0", nv.widget(0).getCondition().getMin());
		assertEquals("1", nv.widget(0).getCondition().getMax());
		assertEquals(true, nv.widget(1).isSlider());
		assertEquals("1", nv.widget(1).getCondition().getMin());
		assertEquals("2", nv.widget(1).getCondition().getMax());

		// �z��\�L(�����G���[�̗v�f��text)
		nv.setWidgetAndCondition("slider", "0<x<1, 1<x<2a");
		assertEquals(2, nv.widgetSize());
		assertEquals(true, nv.widget(0).isSlider());
		assertEquals("0", nv.widget(0).getCondition().getMin());
		assertEquals("1", nv.widget(0).getCondition().getMax());
		assertEquals(false, nv.widget(1).isSlider());
		assertEquals(true, nv.widget(1).isText());
		assertEquals(true, nv.widget(1).getCondition().isNull());

		// �n�b�V���\�L
		nv.setWidgetAndCondition("slider", "{key0:0<x<1, key1: 1<x<2 }");
		assertEquals(2, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isSlider());
		assertEquals("0", nv.widget("key0").getCondition().getMin());
		assertEquals("1", nv.widget("key0").getCondition().getMax());
		assertEquals(true, nv.widget("key1").isSlider());
		assertEquals("1", nv.widget("key1").getCondition().getMin());
		assertEquals("2", nv.widget("key1").getCondition().getMax());

		// �n�b�V���\�L(�����G���[�̗v�f��text)
		nv.setWidgetAndCondition("slider", "{ key0:0<x<1, key1: 1<x< }");
		assertEquals(2, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isSlider());
		assertEquals("0", nv.widget("key0").getCondition().getMin());
		assertEquals("1", nv.widget("key0").getCondition().getMax());
		assertEquals(false, nv.widget("key1").isSlider());
		assertEquals(true, nv.widget("key1").isText());
		assertEquals(true, nv.widget("key1").getCondition().isNull());
	}

	public void testLoadWidgetValue() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", null);
		nv.setValue("1, 2,3");
		// ��������Ȃ�
		nv.setWidgetAndCondition("text", null);
		nv.loadWidgetValue();
		assertEquals(true, nv.widget().isText());
		assertEquals(false, nv.widget().isValueModified());
		assertEquals("1, 2,3", nv.widget().getValue());
		nv.widget().setValue("4");
		assertEquals(true, nv.widget().isValueModified());
		// �ҏW����ԃ`�F�b�N
		assertEquals(true, nv.isLoadedWidgetValue());
		nv.saveWidgetValue();
		assertEquals(false, nv.isLoadedWidgetValue());

		// �z��̏ꍇ
		nv.setValue("1, 2,3");

		// �l���z��Awidget���P��
		nv.setWidgetAndCondition("slider", "0<x<100");
		nv.loadWidgetValue();
		assertEquals(true, nv.widget().isSlider());
		assertEquals(false, nv.widget().isValueModified());
		assertEquals("1, 2,3", nv.widget().getValue());
		nv.widget().setValue("4");
		assertEquals(true, nv.widget().isValueModified());

		// �z�� (value��widget��葽��)
		nv.setWidgetAndCondition("text", "0<x<1, 1<x<2");
		nv.loadWidgetValue();
		assertEquals(2, nv.widgetSize());
		assertEquals(true, nv.widget(0).isText());
		assertEquals(false, nv.widget(0).isValueModified());
		assertEquals("1", nv.widget(0).getValue());
		assertEquals(true, nv.widget(1).isText());
		assertEquals(false, nv.widget(1).isValueModified());
		assertEquals("2", nv.widget(1).getValue());
		nv.widget(0).setValue("4");
		assertEquals(true, nv.widget(0).isValueModified());
		nv.widget(1).setValue("5");
		assertEquals(true, nv.widget(0).isValueModified());

		// �z�� (value��widget��菭�Ȃ�)
		nv.setWidgetAndCondition("text", "0<x<1, 1<x<2, 2<x<3, 3<x<4");
		nv.loadWidgetValue();
		assertEquals(4, nv.widgetSize());
		assertEquals(true, nv.widget(0).isText());
		assertEquals("1", nv.widget(0).getValue());
		assertEquals(true, nv.widget(1).isText());
		assertEquals("2", nv.widget(1).getValue());
		assertEquals(true, nv.widget(2).isText());
		assertEquals("3", nv.widget(2).getValue());
		assertEquals(true, nv.widget(3).isText());
		assertEquals("", nv.widget(3).getValue());

		// �n�b�V���̏ꍇ
		nv.setValue("{key0:1, key1: 2,key2:3 }");

		// �l���n�b�V���Awidget���P��
		nv.setWidgetAndCondition("slider", "0<x<100");
		nv.loadWidgetValue();
		assertEquals(true, nv.widget().isSlider());
		assertEquals(false, nv.widget().isValueModified());
		assertEquals("{key0:1, key1: 2,key2:3 }", nv.widget().getValue());
		nv.widget().setValue("4");
		assertEquals(true, nv.widget().isValueModified());

		// �n�b�V�� (value��widget��葽��)
		nv.setWidgetAndCondition("text", "{key0: 0<x<1, key1:1<x<2 }");
		nv.loadWidgetValue();
		assertEquals(2, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isText());
		assertEquals(false, nv.widget("key0").isValueModified());
		assertEquals("1", nv.widget("key0").getValue());
		assertEquals(true, nv.widget("key1").isText());
		assertEquals(false, nv.widget("key1").isValueModified());
		assertEquals("2", nv.widget("key1").getValue());
		nv.widget("key0").setValue("4");
		assertEquals(true, nv.widget("key0").isValueModified());
		nv.widget("key1").setValue("5");
		assertEquals(true, nv.widget("key1").isValueModified());

		// �n�b�V�� (value��widget��菭�Ȃ�)
		nv.setWidgetAndCondition("text", "{ key0: 0<x<1, key1:1<x<2, key2: 2<x<3,key3: 3<x<4}");
		nv.loadWidgetValue();
		assertEquals(4, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isText());
		assertEquals("1", nv.widget("key0").getValue());
		assertEquals(true, nv.widget("key1").isText());
		assertEquals("2", nv.widget("key1").getValue());
		assertEquals(true, nv.widget("key2").isText());
		assertEquals("3", nv.widget("key2").getValue());
		assertEquals(true, nv.widget("key3").isText());
		assertEquals("", nv.widget("key3").getValue());
	}

	public void testSaveWidgetValue() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", null);
		// ��������Ȃ�
		nv.setWidgetAndCondition("text", null);
		nv.widget().setValue("1");
		nv.saveWidgetValue();
		assertEquals("1", nv.getValueAsString());
		assertEquals(false, nv.widget().isValueModified());
		nv.widget().setValue("11");
		assertEquals(true, nv.widget().isValueModified());

		// spinner
		nv.setWidgetAndCondition("spinner", "0<x<100");
		nv.widget().setValue("10");
		nv.saveWidgetValue();
		assertEquals("10", nv.getValueAsString());
		assertEquals(false, nv.widget().isValueModified());
		nv.widget().setValue("11");
		assertEquals(true, nv.widget().isValueModified());

		// �z��
		nv.setWidgetAndCondition("text", "0<x<1, 1<x<2");
		nv.widget(0).setValue("0.1");
		nv.widget(1).setValue("1.1");
		nv.saveWidgetValue();
		assertEquals("0.1, 1.1", nv.getValueAsString());
		assertEquals(false, nv.widget(0).isValueModified());
		nv.widget(0).setValue("11");
		assertEquals(true, nv.widget(0).isValueModified());
		assertEquals(false, nv.widget(1).isValueModified());
		nv.widget(1).setValue("11");
		assertEquals(true, nv.widget(1).isValueModified());

		// �n�b�V��
		nv.setWidgetAndCondition("text", "{key0:0<x<1, key1:1<x<2 }");
		nv.widget("key0").setValue("0.1");
		nv.widget("key1").setValue("1.1");
		nv.saveWidgetValue();
		assertEquals("{key0: 0.1, key1: 1.1}", nv.getValueAsString());
		assertEquals(false, nv.widget("key0").isValueModified());
		nv.widget("key0").setValue("11");
		assertEquals(true, nv.widget("key0").isValueModified());
		assertEquals(false, nv.widget("key1").isValueModified());
		nv.widget("key1").setValue("11");
		assertEquals(true, nv.widget("key1").isValueModified());
	}

	public void testParseMap() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", null);
		Map<String, String> map;

		// {key0:1, key1: 2,key2:3 }
		map = nv.parseMap("{key0:1, key1: 2,key2:3 }");
		assertEquals(3, map.keySet().size());
		assertEquals("1", map.get("key0"));
		assertEquals("2", map.get("key1"));
		assertEquals("3", map.get("key2"));

		// {key0:, key1: 2 } // �l�Ȃ�
		map = nv.parseMap("{key0:, key1: 2 }");
		assertEquals(2, map.keySet().size());
		assertEquals("", map.get("key0"));
		assertEquals("2", map.get("key1"));

		// {:1, key1: 2 } // �L�[�Ȃ�
		map = nv.parseMap("{:1, key1: 2 }");
		assertEquals(1, map.keySet().size());
		assertEquals("2", map.get("key1"));
	}

	public void testClone() throws Exception {
		String value = "hoge";
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", value);
		nv.setWidgetAndCondition("slider", "0<x<100");
		NamedValueConfigurationWrapper clone = nv.clone();
		assertEquals(nv.getKey(), clone.getKey());
		assertEquals(nv.getValueAsString(), clone.getValueAsString());
		assertEquals(nv.isKeyModified(), clone.isKeyModified());
		assertEquals(nv.isValueModified(), clone.isValueModified());
		assertEquals(nv.widgetSize(), clone.widgetSize());
		assertEquals(nv.widgetKeySet(), clone.widgetKeySet());
	}
}
