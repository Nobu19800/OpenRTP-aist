package jp.go.aist.rtm.toolscommon.model.core.validation;


/**
 * A sample validator interface for {@link jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CorbaWrapperObjectValidator {
	boolean validate();

	boolean validateCorbaObject(org.omg.CORBA.Object value);
}