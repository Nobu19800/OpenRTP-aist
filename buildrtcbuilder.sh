#!/bin/sh
#===========================================================================
# update:
# cerate:Sep/11/2008
# 
# ���Υ����륹����ץȤ� RTCBuilder ��ӥ�ɤ��ޤ���
#     �ʰʹߡ�"Update"��˾嵭�����ա�̾�����ѹ����Ƥ򵭽Ҥ����
# ���Υ����륹����ץȤ� RTCBuilder ��ӥ�ɤ��ޤ���
# �Ķ�
#  �ʲ��˥ӥ�ɤ�ɬ�פʴĶ��ȥ��󥹥ȡ�����ˡ���ñ�˼����ޤ���
#   Eclipse SDK
#   ant
#   jdk
# 
# �Ķ��ѿ�
#  �ӥ�ɤ�ɬ�פʴĶ��ѿ���ʲ��˼����ޤ���
#   ECLIPSE_HOME   Eclipse SDK 3.2.x �μ¹ԥե����뤬����ǥ��쥯�ȥ�����
#   JAVA_HOME      jdk�Υǥ��쥯�ȥ����ꤷ�ޤ���
#
#===========================================================================

#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
JARDIR=rtcbuilder_1.1.0
LIBS="-lib ../lib -lib $ECLIPSE_HOME/plugins"

#---------------------------------------------------------------------------
#
#---------------------------------------------------------------------------
declare -a build_tbl=(	\
	"jp.go.aist.rtm.toolscommon"	\
	"jp.go.aist.rtm.toolscommon.nl1"	\
	"jp.go.aist.rtm.toolscommon.profiles"	\
	"jp.go.aist.rtm.toolscommon.profiles.nl1"	\
	"jp.go.aist.rtm.rtcbuilder"	\
	"jp.go.aist.rtm.rtcbuilder.nl1"	\
	"jp.go.aist.rtm.rtcbuilder.csharp"	\
	"jp.go.aist.rtm.rtcbuilder.java"	\
	"jp.go.aist.rtm.rtcbuilder.python"	\
	"jp.go.aist.rtm.rtcbuilder.vbdotnet"	\
)


declare	-i num
declare -i ic
num=${#build_tbl[@]}

ic=0
while [ $ic -lt $num ]
do
	if [ -d ${build_tbl[ic]} ]
	then
		echo "-" ${build_tbl[ic]}
		cd ${build_tbl[ic]}
		ant buildAll ${LIBS}
		if [ $? -ne 0 ];
		then 
			exit 1
		fi
		cd ..
	else
		echo ${build_tbl[ic]} "doesn't exist."
	fi
	ic=ic+1
done

#---------------------------------------------------------------------------
# ɬ�פʥե������zip
#
#
#---------------------------------------------------------------------------
mkdir ./${JARDIR}
ic=0
declare name
while [ $ic -lt $num ]
do
	name=${build_tbl[ic]}"_*.jar"
	cp -p ${build_tbl[ic]}/jar/$name ./${JARDIR} 
	ic=ic+1
done
rm ${JARDIR}.zip
zip ${JARDIR}.zip -r ./${JARDIR}/

rm -rf ./${JARDIR}


