<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="uv" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="icon" tagdir="/WEB-INF/tags/icons" %>

<%@attribute name="person" type="org.synyx.urlaubsverwaltung.person.Person" required="true" %>
<%@attribute name="departments" type="java.util.List<org.synyx.urlaubsverwaltung.department.Department>" required="false" %>
<%@attribute name="cssClass" type="java.lang.String" required="false" %>
<%@attribute name="nameIsNoLink" type="java.lang.Boolean" required="false" %>

<spring:url var="URL_PREFIX" value="/web"/>

<uv:box__ className="tw-items-center ${cssClass}">
    <jsp:attribute name="icon">
        <div class="tw-inline-block tw-rounded-full tw-bg-gradient-to-br tw-from-blue-50 tw-to-blue-200 dark:tw-from-sky-800 dark:tw-to-zinc-800 tw-p-1">
            <img
                src="<c:out value='${person.gravatarURL}?d=mm&s=60'/>"
                alt="<spring:message code="gravatar.alt" arguments="${person.niceName}"/>"
                class="gravatar tw-rounded-full"
                width="60px"
                height="60px"
                onerror="this.src !== '/images/gravatar.jpg' && (this.src = '/images/gravatar.jpg')"
            />
        </div>
    </jsp:attribute>
    <jsp:body>
        <div class="tw-text-lg tw-mb-1">
            <c:choose>
                <c:when test="${nameIsNoLink}">
                    <c:out value="${person.niceName}"/>
                </c:when>
                <c:otherwise>
                    <a href="${URL_PREFIX}/person/${person.id}/overview" class="print:no-link">
                        <c:out value="${person.niceName}"/>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
        <a href="mailto:<c:out value='${person.email}'/>" class="tw-mb-1 tw-inline-block tw-text-sm print:no-link">
            <span class="tw-flex tw-items-center">
                <icon:mail className="tw-w-4 tw-h-4" />
                &nbsp;<c:out value="${person.email}"/>
            </span>
        </a>
        <c:if test="${not empty departments}">
            <ul class="tw-p-0 tw-list-none tw-flex tw-flex-wrap tw-text-xs tw-space-x-0.5">
                <c:forEach items="${departments}" var="department">
                    <li class="tw-mb-1 tw-px-1.5 tw-rounded-full tw-bg-green-100 tw-text-green-800">
                        <c:out value="${department.name}" />
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </jsp:body>
</uv:box__>
