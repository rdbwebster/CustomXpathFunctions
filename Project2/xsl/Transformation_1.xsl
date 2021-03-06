<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../FileEmployee.xsd"/>
      <rootElement name="Employees" namespace="http://www.example.org"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../FileEmployee.xsd"/>
      <rootElement name="Employees" namespace="http://www.example.org"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.5.0(build 110406.1400.11) AT [MON JUN 06 16:23:59 EDT 2011]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:geo="http://www.oracle.com/XSL/Transform/java/com.example.reusable.asset.CheckDept"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ns0="http://www.example.org"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:mf="http://www.oracle.com/XSL/Transform/java/com.example.reusable.asset"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl ns0 xsd xp20 bpws geo mhdr bpel oraext dvm hwf med ids bpm mf xdk xref bpmn ora socket ldap">
  <xsl:template match="/">
    <ns0:Employees>
      <xsl:for-each select="/ns0:Employees/ns0:Emp">
        <ns0:Emp>
          <ns0:number>
            <xsl:value-of select="ns0:number"/>
          </ns0:number>
          <ns0:firstName>
            <xsl:if test="ns0:firstName/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:firstName/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:firstName"/>
          </ns0:firstName>
          <ns0:lastName>
            <xsl:if test="ns0:lastName/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:lastName/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:lastName"/>
          </ns0:lastName>
          <ns0:job>
            <xsl:if test="ns0:job/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:job/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:job"/>
          </ns0:job>
          <ns0:hiredate>
            <xsl:if test="ns0:hiredate/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:hiredate/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:hiredate"/>
          </ns0:hiredate>
          <ns0:dept>
            <xsl:if test="ns0:dept/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:dept/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="geo:checkDept(ns0:dept)"/>
          </ns0:dept>
        </ns0:Emp>
      </xsl:for-each>
    </ns0:Employees>
  </xsl:template>
</xsl:stylesheet>
