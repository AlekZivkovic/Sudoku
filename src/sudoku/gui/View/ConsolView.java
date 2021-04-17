package sudoku.gui.View;

import sudoku.gui.MainFrame;
import sudoku.gui.model.DataBase;
import sudoku.utils.GUIUtils;
import sudoku.repository.Polje;
import sudoku.repository.Teren;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConsolView extends BorderPane {
        protected final TextArea outPut = new TextArea();
        protected final TextField inPut = new TextField();

        protected final List<String> history = new ArrayList<>();
        protected int historyPointer = 0;

        private List<String> messageReceived;

        public ConsolView() {
            initElem();
          addElem();
        }

    private void initElem() {
        messageReceived = Arrays.asList("close","goBack");
    }

    private void addElem() {
        outPut.setEditable(false);
        setRight(outPut);

        inPut.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case ENTER:
                    String text = inPut.getText();
                    outPut.appendText(text + System.lineSeparator());
                    history.add(text);
                    historyPointer++;
                    if (messageReceived.contains(text)) {
                        handle(text);
                    }
                    inPut.clear();
                    break;
                case UP:
                    if (historyPointer == 0) {
                        break;
                    }
                    historyPointer--;
                    GUIUtils.runSafe(() -> {
                        inPut.setText(history.get(historyPointer));
                        inPut.selectAll();
                    });
                    break;
                case DOWN:
                    if (historyPointer == history.size() - 1) {
                        break;
                    }
                    historyPointer++;
                    GUIUtils.runSafe(() -> {
                        inPut.setText(history.get(historyPointer));
                        inPut.selectAll();
                    });
                    break;
                default:
                    break;
            }
        });
        setBottom(inPut);
        }

    private void handle(String text) {
    //close//goBack;
        switch (text){
            case "close" :
                MainFrame.getInstance().close();break;

            case "goBack":
                for(Polje p: Teren.getTeren().getPolja()) {
                    p.dodajBroj(0);
                    p.setPosedujeBroj(false);
                }
                this.clear();
                DataBase.getInstance().setGeneRed(true);
                MainFrame.getInstance().setScene(new Scene(new PickerView(),300,350));
                break;
            default: break;
        }

        }

    @Override
        public void requestFocus() {
            super.requestFocus();
            inPut.requestFocus();
        }

        public void setMessageReceived(final List<String> messageReceived) {
            this.messageReceived = messageReceived;
        }

        public void clear() {
            GUIUtils.runSafe(() -> outPut.clear());
        }

        public void print(final String text) {
            Objects.requireNonNull(text, "text");
            GUIUtils.runSafe(() -> outPut.appendText(text));
        }

        public void println(final String text) {
            Objects.requireNonNull(text, "text");
            GUIUtils.runSafe(() -> outPut.appendText(text + System.lineSeparator()));
        }

        public void println() {
            GUIUtils.runSafe(() -> outPut.appendText(System.lineSeparator()));
        }
}

