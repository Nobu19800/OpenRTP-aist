// -*-C++-*-
/*!
 * @file  MyServiceSVC_impl.cpp
 * @brief Service implementation code of MyService.idl
 *
 */

#include "MyServiceSVC_impl.h"

/*
 * Example implementational code for IDL interface Test::MyService
 */
Test_MyServiceSVC_impl::Test_MyServiceSVC_impl()
{
  // Please add extra constructor code here.
}


Test_MyServiceSVC_impl::~Test_MyServiceSVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
char* Test_MyServiceSVC_impl::echo(const char* msg)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <char* Test_MyServiceSVC_impl::echo(const char* msg)>"
#endif
  return 0;
}

Test::EchoList* Test_MyServiceSVC_impl::get_echo_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <Test::EchoList* Test_MyServiceSVC_impl::get_echo_history()>"
#endif
  return 0;
}

void Test_MyServiceSVC_impl::set_value(::CORBA::Float value)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <void Test_MyServiceSVC_impl::set_value(::CORBA::Float value)>"
#endif
}

::CORBA::Float Test_MyServiceSVC_impl::get_value()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <::CORBA::Float Test_MyServiceSVC_impl::get_value()>"
#endif
  return 0;
}

Test::ValueList* Test_MyServiceSVC_impl::get_value_history()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <Test::ValueList* Test_MyServiceSVC_impl::get_value_history()>"
#endif
  return 0;
}



// End of example implementational code



