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
#   ant4eclipse
#   jdk
# 
# �Ķ��ѿ�
#  �ӥ�ɤ�ɬ�פʴĶ��ѿ���ʲ��˼����ޤ���
#   ECLIPSE_HOME   Eclipse SDK 3.2.x �μ¹ԥե����뤬����ǥ��쥯�ȥ�����
#   ECLIPSE33_HOME Eclipse SDK 3.3.x �μ¹ԥե����뤬����ǥ��쥯�ȥ�����
#   ANT_HOME       ant �Υǥ��쥯�ȥ����ꤷ�ޤ���
#   JAVA_HOME      jdk�Υǥ��쥯�ȥ����ꤷ�ޤ���
#
#===========================================================================

#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
set DUMMY=$ANT_HOME
export ANT_HOME=$ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/
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
		ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
		if [ $? -ne 0 ];
		then 
			set ANT_HOME=$DUMMY
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
mkdir ./rtcbuilder_1.0.0
ic=0
declare name
while [ $ic -lt $num ]
do
	name=${build_tbl[ic]}"_1.0.0.jar"
	cp -p ${build_tbl[ic]}/jar/$name ./rtcbuilder_1.0.0 
	ic=ic+1
done
rm rtcbuilder_1.0.0.zip
zip rtcbuilder_1.0.0.zip -r ./rtcbuilder_1.0.0/

rm -rf ./rtcbuilder_1.0.0


#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
set ANT_HOME=$DUMMY


