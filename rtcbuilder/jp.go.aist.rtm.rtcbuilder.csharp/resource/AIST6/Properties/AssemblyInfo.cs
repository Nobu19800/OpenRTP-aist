using System.Reflection;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;

// �A�Z���u���Ɋւ����ʏ��͈ȉ��̑����Z�b�g���Ƃ����Đ��䂳��܂��B 
// �A�Z���u���Ɋ֘A�t�����Ă������ύX����ɂ́A
// �����̑����l��ύX���Ă��������B
[assembly: AssemblyTitle("bar")]
[assembly: AssemblyDescription("bartest")]
[assembly: AssemblyConfiguration("")]
[assembly: AssemblyCompany("Tec")]
[assembly: AssemblyProduct("bar")]
[assembly: AssemblyCopyright("Copyright (c) Tec")]
[assembly: AssemblyTrademark("")]
[assembly: AssemblyCulture("")]

// ComVisible �� false �ɐݒ肷��ƁA���̃A�Z���u�����̌^�� COM �R���|�[�l���g�ɂ� 
// �Q�ƕs�\�ɂȂ�܂��BCOM ���炱�̃A�Z���u�����̌^�ɃA�N�Z�X����ꍇ�́A 
// ���̌^�� ComVisible ������ true �ɐݒ肵�Ă��������B
[assembly: ComVisible(false)]

// ���� GUID �́A���̃v���W�F�N�g�� COM �Ɍ��J�����ꍇ�́Atypelib �� ID �ł�
[assembly: Guid("42415f99-e546-4ad7-b017-3c2e6260e83d")]

// �A�Z���u���̃o�[�W�������́A�ȉ��� 4 �̒l�ō\������Ă��܂�:
//
//      Major Version
//      Minor Version 
//      Build Number
//      Revision
//
[assembly: AssemblyVersion("1.0.0.0")]
[assembly: AssemblyFileVersion("1.0.0.0")]

// log4net�𗘗p���邽�߂̐ݒ�
[assembly: log4net.Config.XmlConfigurator(Watch = true)]
