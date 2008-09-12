package jp.go.aist.rtm.toolscommon.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * NullObject���쐬����N���X
 */
public class NullObjectCreater {
	/**
	 * NullObject���쐬����
	 * 
	 * @param clazz
	 *            �쐬����NullObject�̌^
	 * @return NullObject
	 */
	public static Object createNullObject(Class[] clazz) {
		Object result = Proxy.newProxyInstance(NullObjectCreater.class
				.getClassLoader(), clazz, new InvocationHandler() {
			public Object invoke(Object obj, Method method, Object[] arts)
					throws Throwable {
				Object result = null;
				Class<?> returnType = method.getReturnType();
				if (returnType != null) {
					try {
						result = returnType.newInstance();
					} catch (Exception e) {
						if (returnType.isInterface()) {
							result = createNullObject(new Class[] { returnType });
						} else {
							result = createNullObject(returnType
									.getInterfaces());
						}
					}
				}

				return result;
			}
		});
		return result;
	}
}
