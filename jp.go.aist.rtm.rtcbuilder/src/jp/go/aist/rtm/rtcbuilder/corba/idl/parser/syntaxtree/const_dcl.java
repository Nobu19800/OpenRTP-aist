//
// Generated by JTB 1.3.2
//

package jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree;

/**
 * Grammar production:
 * nodeToken -> "const"
 * const_type -> const_type()
 * identifier -> identifier()
 * nodeToken1 -> "="
 * const_exp -> const_exp()
 */
public class const_dcl implements Node {
   private Node parent;
   public NodeToken nodeToken;
   public const_type const_type;
   public identifier identifier;
   public NodeToken nodeToken1;
   public const_exp const_exp;

   public const_dcl(NodeToken n0, const_type n1, identifier n2, NodeToken n3, const_exp n4) {
      nodeToken = n0;
      if ( nodeToken != null ) nodeToken.setParent(this);
      const_type = n1;
      if ( const_type != null ) const_type.setParent(this);
      identifier = n2;
      if ( identifier != null ) identifier.setParent(this);
      nodeToken1 = n3;
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      const_exp = n4;
      if ( const_exp != null ) const_exp.setParent(this);
   }

   public const_dcl(const_type n0, identifier n1, const_exp n2) {
      nodeToken = new NodeToken("const");
      if ( nodeToken != null ) nodeToken.setParent(this);
      const_type = n0;
      if ( const_type != null ) const_type.setParent(this);
      identifier = n1;
      if ( identifier != null ) identifier.setParent(this);
      nodeToken1 = new NodeToken("=");
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      const_exp = n2;
      if ( const_exp != null ) const_exp.setParent(this);
   }

   public void accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

