//
// Generated by JTB 1.3.2
//

package jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.*;
import java.util.*;

/**
 * All GJ visitors must implement this interface.
 */

public interface GJVisitor<R,A> {

   //
   // GJ Auto class visitors
   //

   public R visit(NodeList n, A argu);
   public R visit(NodeListOptional n, A argu);
   public R visit(NodeOptional n, A argu);
   public R visit(NodeSequence n, A argu);
   public R visit(NodeToken n, A argu);

   //
   // User-generated visitor methods below
   //

   /**
    * nodeList -> ( definition() )+
    */
   public R visit(specification n, A argu);

   /**
    * nodeChoice -> type_dcl() ";"
    *       | const_dcl() ";"
    *       | except_dcl() ";"
    *       | interfacex() ";"
    *       | module() ";"
    */
   public R visit(definition n, A argu);

   /**
    * nodeToken -> "module"
    * identifier -> identifier()
    * nodeToken1 -> "{"
    * nodeList -> ( definition() )+
    * nodeToken2 -> "}"
    */
   public R visit(module n, A argu);

   /**
    * nodeChoice -> interface_dcl()
    *       | forward_dcl()
    */
   public R visit(interfacex n, A argu);

   /**
    * interface_header -> interface_header()
    * nodeToken -> "{"
    * interface_body -> interface_body()
    * nodeToken1 -> "}"
    */
   public R visit(interface_dcl n, A argu);

   /**
    * nodeToken -> "interface"
    * identifier -> identifier()
    */
   public R visit(forward_dcl n, A argu);

   /**
    * nodeToken -> "interface"
    * identifier -> identifier()
    * nodeOptional -> [ inheritance_spec() ]
    */
   public R visit(interface_header n, A argu);

   /**
    * nodeListOptional -> ( export() )*
    */
   public R visit(interface_body n, A argu);

   /**
    * nodeChoice -> type_dcl() ";"
    *       | const_dcl() ";"
    *       | except_dcl() ";"
    *       | attr_dcl() ";"
    *       | op_dcl() ";"
    */
   public R visit(export n, A argu);

   /**
    * nodeToken -> ":"
    * scoped_name -> scoped_name()
    * nodeListOptional -> ( "," scoped_name() )*
    */
   public R visit(inheritance_spec n, A argu);

   /**
    * nodeOptional -> [ "::" ]
    * identifier -> identifier()
    * nodeListOptional -> ( "::" identifier() )*
    */
   public R visit(scoped_name n, A argu);

   /**
    * nodeToken -> "const"
    * const_type -> const_type()
    * identifier -> identifier()
    * nodeToken1 -> "="
    * const_exp -> const_exp()
    */
   public R visit(const_dcl n, A argu);

   /**
    * nodeChoice -> integer_type()
    *       | char_type()
    *       | boolean_type()
    *       | floating_pt_type()
    *       | string_type()
    *       | scoped_name()
    */
   public R visit(const_type n, A argu);

   /**
    * or_expr -> or_expr()
    */
   public R visit(const_exp n, A argu);

   /**
    * xor_expr -> xor_expr()
    * nodeListOptional -> ( "|" xor_expr() )*
    */
   public R visit(or_expr n, A argu);

   /**
    * and_expr -> and_expr()
    * nodeListOptional -> ( "^" and_expr() )*
    */
   public R visit(xor_expr n, A argu);

   /**
    * shift_expr -> shift_expr()
    * nodeListOptional -> ( "&" shift_expr() )*
    */
   public R visit(and_expr n, A argu);

   /**
    * add_expr -> add_expr()
    * nodeListOptional -> ( ( ">>" | "<<" ) add_expr() )*
    */
   public R visit(shift_expr n, A argu);

   /**
    * mult_expr -> mult_expr()
    * nodeListOptional -> ( ( "+" | "-" ) mult_expr() )*
    */
   public R visit(add_expr n, A argu);

   /**
    * unary_expr -> unary_expr()
    * nodeListOptional -> ( ( "*" | "/" | "%" ) unary_expr() )*
    */
   public R visit(mult_expr n, A argu);

   /**
    * nodeOptional -> [ unary_operator() ]
    * primary_expr -> primary_expr()
    */
   public R visit(unary_expr n, A argu);

   /**
    * nodeChoice -> "-"
    *       | "+"
    *       | "~"
    */
   public R visit(unary_operator n, A argu);

   /**
    * nodeChoice -> scoped_name()
    *       | literal()
    *       | "(" const_exp() ")"
    */
   public R visit(primary_expr n, A argu);

   /**
    * nodeChoice -> integer_literal()
    *       | string_literal()
    *       | character_literal()
    *       | floating_pt_literal()
    *       | boolean_literal()
    */
   public R visit(literal n, A argu);

   /**
    * nodeChoice -> "TRUE"
    *       | "FALSE"
    */
   public R visit(boolean_literal n, A argu);

   /**
    * const_exp -> const_exp()
    */
   public R visit(positive_int_const n, A argu);

   /**
    * nodeChoice -> "typedef" type_declarator()
    *       | struct_type()
    *       | union_type()
    *       | enum_type()
    */
   public R visit(type_dcl n, A argu);

   /**
    * type_spec -> type_spec()
    * declarators -> declarators()
    */
   public R visit(type_declarator n, A argu);

   /**
    * nodeChoice -> simple_type_spec()
    *       | constr_type_spec()
    */
   public R visit(type_spec n, A argu);

   /**
    * nodeChoice -> base_type_spec()
    *       | template_type_spec()
    *       | scoped_name()
    */
   public R visit(simple_type_spec n, A argu);

   /**
    * nodeChoice -> floating_pt_type()
    *       | integer_type()
    *       | char_type()
    *       | boolean_type()
    *       | octet_type()
    *       | any_type()
    */
   public R visit(base_type_spec n, A argu);

   /**
    * nodeChoice -> sequence_type()
    *       | string_type()
    */
   public R visit(template_type_spec n, A argu);

   /**
    * nodeChoice -> struct_type()
    *       | union_type()
    *       | enum_type()
    */
   public R visit(constr_type_spec n, A argu);

   /**
    * declarator -> declarator()
    * nodeListOptional -> ( "," declarator() )*
    */
   public R visit(declarators n, A argu);

   /**
    * nodeChoice -> complex_declarator()
    *       | simple_declarator()
    */
   public R visit(declarator n, A argu);

   /**
    * identifier -> identifier()
    */
   public R visit(simple_declarator n, A argu);

   /**
    * array_declarator -> array_declarator()
    */
   public R visit(complex_declarator n, A argu);

   /**
    * nodeChoice -> "float"
    *       | "double"
    */
   public R visit(floating_pt_type n, A argu);

   /**
    * nodeChoice -> signed_int()
    *       | unsigned_int()
    */
   public R visit(integer_type n, A argu);

   /**
    * nodeChoice -> signed_long_long_int()
    *       | signed_long_double_int()
    *       | signed_long_int()
    *       | signed_short_int()
    */
   public R visit(signed_int n, A argu);

   /**
    * nodeToken -> "long"
    */
   public R visit(signed_long_int n, A argu);

   /**
    * nodeToken -> "short"
    */
   public R visit(signed_short_int n, A argu);

   /**
    * nodeToken -> "long"
    * nodeToken1 -> "long"
    */
   public R visit(signed_long_long_int n, A argu);

   /**
    * nodeToken -> "long"
    * nodeToken1 -> "double"
    */
   public R visit(signed_long_double_int n, A argu);

   /**
    * nodeChoice -> unsigned_long_long_int()
    *       | unsigned_long_int()
    *       | unsigned_short_int()
    */
   public R visit(unsigned_int n, A argu);

   /**
    * nodeToken -> "unsigned"
    * nodeToken1 -> "long"
    */
   public R visit(unsigned_long_int n, A argu);

   /**
    * nodeToken -> "unsigned"
    * nodeToken1 -> "short"
    */
   public R visit(unsigned_short_int n, A argu);

   /**
    * nodeToken -> "unsigned"
    * nodeToken1 -> "long"
    * nodeToken2 -> "long"
    */
   public R visit(unsigned_long_long_int n, A argu);

   /**
    * nodeToken -> "char"
    */
   public R visit(char_type n, A argu);

   /**
    * nodeToken -> "boolean"
    */
   public R visit(boolean_type n, A argu);

   /**
    * nodeToken -> "octet"
    */
   public R visit(octet_type n, A argu);

   /**
    * nodeToken -> "any"
    */
   public R visit(any_type n, A argu);

   /**
    * nodeToken -> "struct"
    * identifier -> identifier()
    * nodeToken1 -> "{"
    * member_list -> member_list()
    * nodeToken2 -> "}"
    */
   public R visit(struct_type n, A argu);

   /**
    * nodeList -> ( member() )+
    */
   public R visit(member_list n, A argu);

   /**
    * type_spec -> type_spec()
    * declarators -> declarators()
    * nodeToken -> ";"
    */
   public R visit(member n, A argu);

   /**
    * nodeToken -> "union"
    * identifier -> identifier()
    * nodeToken1 -> "switch"
    * nodeToken2 -> "("
    * switch_type_spec -> switch_type_spec()
    * nodeToken3 -> ")"
    * nodeToken4 -> "{"
    * switch_body -> switch_body()
    * nodeToken5 -> "}"
    */
   public R visit(union_type n, A argu);

   /**
    * nodeChoice -> integer_type()
    *       | char_type()
    *       | boolean_type()
    *       | enum_type()
    *       | scoped_name()
    */
   public R visit(switch_type_spec n, A argu);

   /**
    * nodeList -> ( casex() )+
    */
   public R visit(switch_body n, A argu);

   /**
    * nodeList -> ( case_label() )+
    * element_spec -> element_spec()
    * nodeToken -> ";"
    */
   public R visit(casex n, A argu);

   /**
    * nodeChoice -> "case" const_exp() ":"
    *       | "default" ":"
    */
   public R visit(case_label n, A argu);

   /**
    * type_spec -> type_spec()
    * declarator -> declarator()
    */
   public R visit(element_spec n, A argu);

   /**
    * nodeToken -> "enum"
    * identifier -> identifier()
    * nodeToken1 -> "{"
    * enumerator -> enumerator()
    * nodeListOptional -> ( "," enumerator() )*
    * nodeToken2 -> "}"
    */
   public R visit(enum_type n, A argu);

   /**
    * identifier -> identifier()
    */
   public R visit(enumerator n, A argu);

   /**
    * nodeToken -> "sequence"
    * nodeToken1 -> "<"
    * simple_type_spec -> simple_type_spec()
    * nodeOptional -> [ "," positive_int_const() ]
    * nodeToken2 -> ">"
    */
   public R visit(sequence_type n, A argu);

   /**
    * nodeToken -> "string"
    * nodeOptional -> [ "<" positive_int_const() ">" ]
    */
   public R visit(string_type n, A argu);

   /**
    * identifier -> identifier()
    * nodeList -> ( fixed_array_size() )+
    */
   public R visit(array_declarator n, A argu);

   /**
    * nodeToken -> "["
    * positive_int_const -> positive_int_const()
    * nodeToken1 -> "]"
    */
   public R visit(fixed_array_size n, A argu);

   /**
    * nodeOptional -> [ "readonly" ]
    * nodeToken -> "attribute"
    * param_type_spec -> param_type_spec()
    * simple_declarator -> simple_declarator()
    * nodeListOptional -> ( "," simple_declarator() )*
    */
   public R visit(attr_dcl n, A argu);

   /**
    * nodeToken -> "exception"
    * identifier -> identifier()
    * nodeOptional -> ( "{" )?
    * nodeListOptional -> ( member() | "exception_body" )*
    * nodeOptional1 -> ( "}" )?
    */
   public R visit(except_dcl n, A argu);

   /**
    * nodeOptional -> [ op_attribute() ]
    * op_type_spec -> op_type_spec()
    * identifier -> identifier()
    * parameter_dcls -> parameter_dcls()
    * nodeOptional1 -> [ raises_expr() ]
    * nodeOptional2 -> [ context_expr() ]
    */
   public R visit(op_dcl n, A argu);

   /**
    * nodeToken -> "oneway"
    */
   public R visit(op_attribute n, A argu);

   /**
    * nodeChoice -> param_type_spec()
    *       | "void"
    */
   public R visit(op_type_spec n, A argu);

   /**
    * nodeToken -> "("
    * nodeOptional -> [ param_dcl() ( "," param_dcl() )* ]
    * nodeToken1 -> ")"
    */
   public R visit(parameter_dcls n, A argu);

   /**
    * param_attribute -> param_attribute()
    * param_type_spec -> param_type_spec()
    * simple_declarator -> simple_declarator()
    */
   public R visit(param_dcl n, A argu);

   /**
    * nodeChoice -> "in"
    *       | "out"
    *       | "inout"
    */
   public R visit(param_attribute n, A argu);

   /**
    * nodeToken -> "raises"
    * nodeToken1 -> "("
    * scoped_name -> scoped_name()
    * nodeListOptional -> ( "," scoped_name() )*
    * nodeToken2 -> ")"
    */
   public R visit(raises_expr n, A argu);

   /**
    * nodeToken -> "context"
    * nodeToken1 -> "("
    * string_literal -> string_literal()
    * nodeListOptional -> ( "," string_literal() )*
    * nodeToken2 -> ")"
    */
   public R visit(context_expr n, A argu);

   /**
    * nodeChoice -> base_type_spec()
    *       | string_type()
    *       | scoped_name()
    */
   public R visit(param_type_spec n, A argu);

   /**
    * nodeToken -> <ID>
    */
   public R visit(identifier n, A argu);

   /**
    * nodeChoice -> <OCTALINT>
    *       | <DECIMALINT>
    *       | <HEXADECIMALINT>
    */
   public R visit(integer_literal n, A argu);

   /**
    * nodeToken -> <STRING>
    */
   public R visit(string_literal n, A argu);

   /**
    * nodeToken -> <CHARACTER>
    */
   public R visit(character_literal n, A argu);

   /**
    * nodeChoice -> <FLOATONE>
    *       | <FLOATTWO>
    */
   public R visit(floating_pt_literal n, A argu);

}
