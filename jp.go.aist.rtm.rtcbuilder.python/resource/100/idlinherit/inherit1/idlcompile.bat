echo off
setlocal
for %%I in (python.exe) do if exist %%~$path:I set f=%%~$path:I
if exist %f% do (
  %f:python.exe=%omniidl.exe -bpython -I"%RTM_ROOT%rtm\idl" -I"I:\GlobalAssist\EclipseAISTRep45\jp.go.aist.rtm.rtcbuilder.python\resource/100/idlinherit/inherit1" idl/MyServiceChildMulti.idl 
) else do (
  echo "python.exe" can not be found.
  echo Please modify PATH environmental variable for python command.
)
endlocal
