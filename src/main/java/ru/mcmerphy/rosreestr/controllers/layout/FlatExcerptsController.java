package ru.mcmerphy.rosreestr.controllers.layout;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.mcmerphy.rosreestr.controllers.layout.components.table.cells.AddressTableCell;
import ru.mcmerphy.rosreestr.controllers.layout.components.table.cells.RightsTableCell;
import ru.mcmerphy.rosreestr.controllers.layout.components.table.cells.StatusTableCell;
import ru.mcmerphy.rosreestr.controllers.layout.components.table.cells.TotalAreaTableCell;
import ru.mcmerphy.rosreestr.controllers.util.Comparators;
import ru.mcmerphy.rosreestr.model.ApplicationModel;
import ru.mcmerphy.rosreestr.model.domain.Address;
import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;
import ru.mcmerphy.rosreestr.model.domain.Right;

import java.math.BigDecimal;

import static ru.mcmerphy.rosreestr.controllers.util.Comparators.INCONSISTENCY_COMPARATOR;
import static ru.mcmerphy.rosreestr.controllers.util.Comparators.RIGHTS_COMPARATOR;

/**
 * Represents controls of all flat excerpts.
 */
public class FlatExcerptsController {

    @FXML
    private TableView<FlatExcerpt> table;

    @FXML
    private TableColumn<FlatExcerpt, Address> addressColumn;

    @FXML
    private TableColumn<FlatExcerpt, BigDecimal> totalAreaColumn;

    @FXML
    private TableColumn<FlatExcerpt, ObservableList<String>> statusColumn;

    @FXML
    private TableColumn<FlatExcerpt, ObservableList<Right>> rightsColumn;

    public void init(ApplicationModel model) {
        table.setItems(model.getFlatExcerpts());
        handleTableColumns();

        Platform.runLater(() -> {
            table.requestFocus();
            table.getSelectionModel().selectFirst();
        });
    }

    public FlatExcerpt getCurrentFlatExcerpt() throws NoCurrentFlatExcerptException {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            return table.getItems().get(selectedIndex);
        } else {
            throw new NoCurrentFlatExcerptException();
        }
    }

    private void handleTableColumns() {
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().getFlat().addressProperty());
        addressColumn.setCellFactory(param -> new AddressTableCell());
        addressColumn.setComparator(Comparators.ADDRESS_COMPARATOR);

        totalAreaColumn.setCellValueFactory(cellData -> cellData.getValue().getFlat().totalAreaProperty());
        totalAreaColumn.setCellFactory(param -> new TotalAreaTableCell());

        statusColumn.setCellValueFactory(cellData -> cellData.getValue().getFlat().inconsistencyProperty());
        statusColumn.setCellFactory(param -> new StatusTableCell());
        statusColumn.setComparator(INCONSISTENCY_COMPARATOR);

        rightsColumn.setCellValueFactory(cellData -> cellData.getValue().getFlat().rightsProperty());
        rightsColumn.setCellFactory(param -> new RightsTableCell());
        rightsColumn.setComparator(RIGHTS_COMPARATOR);
    }

}
