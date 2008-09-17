#!/bin/sh
#===========================================================================
# update:
# cerate:Sep/11/2008
# 
# ���Υ����륹����ץȤ� RTSystemEditor ��ӥ�ɤ��ޤ���
#     �ʰʹߡ�"Update"��˾嵭�����ա�̾�����ѹ����Ƥ򵭽Ҥ����
# ���Υ����륹����ץȤ� RTSystemEditor ��ӥ�ɤ��ޤ���
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
#
#

#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
set DUMMY=$ANT_HOME
export ANT_HOME=$ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/


#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.fsmcbuilder ]
then
    echo "-jp.go.aist.rtm.fsmcbuilder "
    cd jp.go.aist.rtm.fsmcbuilder 
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
        exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.fsmcbuilder doesn't exist."
fi

#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.toolscommon.profiles ]
then
    echo "-jp.go.aist.rtm.toolscommon.profiles"
    cd jp.go.aist.rtm.toolscommon.profiles
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.toolscommon.profiles doesn't exist."
fi


#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.toolscommon ]
then
    echo "-jp.go.aist.rtm.toolscommon"
    cd jp.go.aist.rtm.toolscommon
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
        exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.toolscommon doesn't exist."
fi

#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.nameserviceview ]
then
    echo "-jp.go.aist.rtm.nameserviceview"
    cd jp.go.aist.rtm.nameserviceview 
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.nameserviceview doesn't exist."
fi


#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.repositoryView ]
then
    echo "-jp.go.aist.rtm.repositoryView"
    cd jp.go.aist.rtm.repositoryView
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.repositoryView doesn't exist."
fi

#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.systemeditor ]
then
    echo "-jp.go.aist.rtm.systemeditor"
    cd jp.go.aist.rtm.systemeditor
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
        exit 1
    fi
    echo "--"
    cd ..
else
    echo "jp.go.aist.rtm.systemeditor doesn't exist."
fi
#---------------------------------------------------------------------------
# ɬ�פʥե������zip
#
#
#---------------------------------------------------------------------------
find ./ -name '*aist*.jar' -exec cp -p {} . \;
rm RTSystemEditor.zip 
zip RTSystemEditor.zip *aist*.jar 
rm *aist*.jar



#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
set ANT_HOME=$DUMMY


