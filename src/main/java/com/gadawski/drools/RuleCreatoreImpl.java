package com.gadawski.drools;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;

public class RuleCreatoreImpl implements RuleCreator {

    @Override
    public String build() {
        PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage();
        packageDescrBuilder
                .name("com.gadawski.drools")
                .newRule()
                .name("HA rule")
                .lhs()
                .pattern().id("date", false).type("RuleDate").constraint("date.getDayOfWeek() == DayOfWeek.SUNDAY").end()
                .end()
                .rhs("$a.showBanner( false );")
                .end();

        String rules = new DrlDumper().dump(packageDescrBuilder.getDescr());

        System.out.println(rules);

        return rules;
    }
//    PackageDescrBuilder packBuilder =
//            DescrFactory.newPackage()
//                    .name( "org.drools.compiler" )
//                    .newRule().name( "r1" )
//                    .lhs()
//                    .and()
//                    .or()
//                    .pattern( "StockTick" ).constraint( "price > 100" ).end()
//                    .pattern( "StockTick" ).constraint( "price < 10" ).end()
//                    .end()
//                    .pattern("StockTick").constraint( "company == \"RHT\"" ).end()
//                    .end()
//                    .end()
//                    .rhs( "    System.out.println(\"foo\");\n" )
//                    .end();
}
