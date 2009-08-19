package RTC;


/**
* RTC/DataFlowComponent.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��46�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief dataFlowComponent
   *
   * @section Description
   *
   * The dataFlowComponent stereotype may be applied to a component
   * type to indicate that its instances should be executed in sorted
   * order by a periodic execution context.
   *
   * @section Constraints
   *
   * - An instance of a component extended by the dataFlowComponent
   *   stereotype must participate in at least one * execution context
   *   of kind PERIODIC, which shall also be used for the execution of
   *   any contained data flow components.
   *
   * - A component extended by dataFlowComponent must realize the
   *   interface DataFlowComponentAction.
   *
   *
   * @endif
   */
public interface DataFlowComponent extends DataFlowComponentOperations, RTC.LightweightRTObject, RTC.DataFlowComponentAction
{
} // interface DataFlowComponent
