# Python stubs generated by omniidl from MyService.idl

import omniORB, _omnipy
from omniORB import CORBA, PortableServer
_0_CORBA = CORBA

_omnipy.checkVersion(2,0, __file__)

try:
    _omniORB_StructBase = omniORB.StructBase
except AttributeError:
    class _omniORB_StructBase: pass


#
# Start of module "_GlobalIDL"
#
__name__ = "_GlobalIDL"
_0__GlobalIDL = omniORB.openModule("_GlobalIDL", r"MyService.idl")
_0__GlobalIDL__POA = omniORB.openModule("_GlobalIDL__POA", r"MyService.idl")


# interface MyService
_0__GlobalIDL._d_MyService = (omniORB.tcInternal.tv_objref, "IDL:MyService:1.0", "MyService")
omniORB.typeMapping["IDL:MyService:1.0"] = _0__GlobalIDL._d_MyService
_0__GlobalIDL.MyService = omniORB.newEmptyClass()
class MyService :
    _NP_RepositoryId = _0__GlobalIDL._d_MyService[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0__GlobalIDL.MyService = MyService
_0__GlobalIDL._tc_MyService = omniORB.tcInternal.createTypeCode(_0__GlobalIDL._d_MyService)
omniORB.registerType(MyService._NP_RepositoryId, _0__GlobalIDL._d_MyService, _0__GlobalIDL._tc_MyService)

# MyService operations and attributes
MyService._d_echo01 = ((omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), (omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), None)
MyService._d_echo02 = ((omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), (omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), None)
MyService._d_echo03 = ((omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), (omniORB.tcInternal.tv_short, ), None)
MyService._d_echo04 = ((), (omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), None)
MyService._d_echo05 = ((omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), (omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short, omniORB.tcInternal.tv_short), None)

# MyService object reference
class _objref_MyService (CORBA.Object):
    _NP_RepositoryId = MyService._NP_RepositoryId

    def __init__(self):
        CORBA.Object.__init__(self)

    def echo01(self, *args):
        return _omnipy.invoke(self, "echo01", _0__GlobalIDL.MyService._d_echo01, args)

    def echo02(self, *args):
        return _omnipy.invoke(self, "echo02", _0__GlobalIDL.MyService._d_echo02, args)

    def echo03(self, *args):
        return _omnipy.invoke(self, "echo03", _0__GlobalIDL.MyService._d_echo03, args)

    def echo04(self, *args):
        return _omnipy.invoke(self, "echo04", _0__GlobalIDL.MyService._d_echo04, args)

    def echo05(self, *args):
        return _omnipy.invoke(self, "echo05", _0__GlobalIDL.MyService._d_echo05, args)

    __methods__ = ["echo01", "echo02", "echo03", "echo04", "echo05"] + CORBA.Object.__methods__

omniORB.registerObjref(MyService._NP_RepositoryId, _objref_MyService)
_0__GlobalIDL._objref_MyService = _objref_MyService
del MyService, _objref_MyService

# MyService skeleton
__name__ = "_GlobalIDL__POA"
class MyService (PortableServer.Servant):
    _NP_RepositoryId = _0__GlobalIDL.MyService._NP_RepositoryId


    _omni_op_d = {"echo01": _0__GlobalIDL.MyService._d_echo01, "echo02": _0__GlobalIDL.MyService._d_echo02, "echo03": _0__GlobalIDL.MyService._d_echo03, "echo04": _0__GlobalIDL.MyService._d_echo04, "echo05": _0__GlobalIDL.MyService._d_echo05}

MyService._omni_skeleton = MyService
_0__GlobalIDL__POA.MyService = MyService
del MyService
__name__ = "_GlobalIDL"

#
# End of module "_GlobalIDL"
#
__name__ = "MyService_idl"

_exported_modules = ( "_GlobalIDL", )

# The end.
