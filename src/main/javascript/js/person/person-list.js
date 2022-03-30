import $ from "jquery";
import List from "list.js";
import "tablesorter";
import { dataValueNumberParser } from "../../components/table-sortable/parser-data-value-number";

$(document).ready(function () {
  $.tablesorter.addParser(dataValueNumberParser);

  const { length: columnCount } = document.querySelectorAll("#person-table thead th");
  const isPersonnelNumberColumnRendered = columnCount === 10;

  if (isPersonnelNumberColumnRendered) {
    $("#person-table").tablesorter({
      sortList: [[2, 0]],
      headers: {
        0: { sorter: false },
        4: { sorter: dataValueNumberParser.id },
        5: { sorter: dataValueNumberParser.id },
        6: { sorter: dataValueNumberParser.id },
        7: { sorter: dataValueNumberParser.id },
        8: { sorter: dataValueNumberParser.id },
        9: { sorter: false },
      },
    });
  } else {
    $("#person-table").tablesorter({
      sortList: [[1, 0]],
      headers: {
        0: { sorter: false },
        3: { sorter: dataValueNumberParser.id },
        4: { sorter: dataValueNumberParser.id },
        5: { sorter: dataValueNumberParser.id },
        6: { sorter: dataValueNumberParser.id },
        7: { sorter: dataValueNumberParser.id },
        8: { sorter: false },
      },
    });
  }

  const options = {
    valueNames: ["firstname", "lastname"],
    page: 500,
  };

  new List("users", options);

  $("#search").on("keypress", function (event) {
    if (event.keyCode === 10 || event.keyCode === 13) event.preventDefault();
  });
});
