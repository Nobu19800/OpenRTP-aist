echo off
setlocal
for %%I in (python.exe) do if exist %%~$path:I set f=%%~$path:I
if exist %f% do (
  %f:python.exe=%omniidl.exe -bpython -I"%RTM_ROOT%rtm\idl" -I"D:\GlobalAssist\EclipseAISTRep\jp.go.aist.rtm.rtcbuilder.python\resource/100/idlinherit/inherit2" idl/MyServiceChildMulti.idl idl/MyServiceChildWithType.idl 
) else do (
  echo "python.exe" can not be found.
  echo Please modify PATH environmental variable for python command.
)
endlocal
