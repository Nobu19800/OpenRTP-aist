package RTC;


/**
* RTC/FsmServiceOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmService
   *
   * @section Description
   *
   * The FsmService interface defines operations necessary for
   * Stimulus Response Processing as an SDO service.
   *
   * @endif
   */
public interface FsmServiceOperations  extends _SDOPackage.SDOServiceOperations
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_fsm_profile
     *
     * @section Description
     *
     * Get the current state of the FSM.
     *
     * @section Semantics
     *
     * Modifications to the object returned by this operation will not
     * be reflected in the FSM until and unless set_fsm_profile is
     * called.
     *
     * @endif
     */
  RTC.FsmProfile get_fsm_profile ();

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief set_fsm_profile
     *
     * @section Description
     *
     * This operation will be used to modify the behavior of an FSM as
     * described in Stimulus Response Processing.
     *
     * @endif
     */
  RTC.ReturnCode_t set_fsm_profile (RTC.FsmProfile fsm_profile);
} // interface FsmServiceOperations
