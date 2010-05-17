package jp.go.aist.rtm.rtcbuilder.python._test;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;

public class PyExCxtTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testExecutionContext() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(3);
		rtcParam.setExecutionRate(5.0);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","","0"));
		configset.add(new ConfigSetParam("int_param1","int","","1"));
		configset.add(new ConfigSetParam("double_param0","double","","0.11"));
		configset.add(new ConfigSetParam("str_param0","String","","hoge"));
		configset.add(new ConfigSetParam("str_param1","String","","dara"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\ExecutionCxt\\";

		checkCode(result, resourceDir, "foo.py");
		checkCode(result, resourceDir, "README.foo");
		checkCode(result, resourceDir, "rtc.conf");
	}
}
