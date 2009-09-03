package RTC;


/**
* RTC/MultiModeComponentActionOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief MultiModeComponentAction
   *
   * MultiModeComponentAction is a companion to ComponentAction that
   is realized by RTCs that support multiple modes.
   *
   * @endif
   */
public interface MultiModeComponentActionOperations 
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_mode_changed
     *
     * @section Description
     *
     * This callback is invoked each time the observed mode of a
     * component has changed with respect to a particular execution
     * context.
     *
     * @section Semantics
     *
     * If the context is PERIODIC, this callback shall come before the
     * next call to on_execute (see Section 5.3.1.2.1) within that
     * context.  The new mode can be retrieved with
     * get_current_mode_in_context. If the result is the same as the
     * result of get_current_mode, the mode has stabilized.
     *
     * @endif
     */
  RTC.ReturnCode_t on_mode_changed (int exec_handle);
} // interface MultiModeComponentActionOperations
