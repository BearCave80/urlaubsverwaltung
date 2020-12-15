Hallo ${recipient.niceName},

es wurde eine neue Abwesenheit erstellt (diese muss nicht genehmigt werden).

    ${baseLinkURL}web/application/${application.id?c}

Informationen zur Abwesenheit:

    Mitarbeiter:               ${application.person.niceName}
    Zeitraum:                  ${application.startDate.format("dd.MM.yyyy")} bis ${application.endDate.format("dd.MM.yyyy")}, ${dayLength}
    Art der Abwesenheit:       ${vacationType}
    <#if (application.reason)?has_content>
    Grund:                     <@compress single_line=true>${application.reason}</@compress>
    </#if>
    <#if application.holidayReplacements?has_content >
    Vertretung:                <#list application.holidayReplacements as replacement>${replacement.person.niceName}<#if !replacement?is_last>, </#if></#list>
    </#if>
    <#if (application.address)?has_content>
    Anschrift/Telefon:         <@compress single_line=true>${application.address}</@compress>
    </#if>
    <#if (comment.text)?has_content>
    Kommentar:                 <@compress single_line=true>${comment.text}</@compress>
    </#if>
    Erstellungsdatum:          ${application.applicationDate.format("dd.MM.yyyy")}