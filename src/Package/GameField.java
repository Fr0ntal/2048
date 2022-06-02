package Package;

public class GameField {

    private int[][] field;
    private int countCellsX;
    private int countCellsY;
    private int winCase;

    private enum DIRECTION {LEFT, RIGHT, UP, DOWN}

    public GameField(int countX, int countY, int winSum) {
        countCellsX = countX;
        countCellsY = countY;
        winCase = winSum;
    }

    public void newGame() {
        clear();
        createNewCell();
        createNewCell();
    }

    int getRandomX() {
        return (int) (Math.random() * countCellsX);
    }

    int getRandomY() {
        return (int) (Math.random() * countCellsY);
    }

    private void createNewCell() {
        while (true) {
            int x = getRandomX();
            int y = getRandomY();
            if (field[x][y] == 0) {
                field[x][y] = 2;
                break;
            }
        }
    }

    private void clear() {
        field = new int[countCellsX][countCellsY];
        for (int x = 0; x < countCellsX; x++) {
            for (int y = 0; y < countCellsY; y++) {
                field[x][y] = 0;
            }
        }
    }

    public void left() {
        move(DIRECTION.LEFT);
    }

    public void right() {
        move(DIRECTION.RIGHT);
    }

    public void down() {
        move(DIRECTION.DOWN);
    }

    public void up() {
        move(DIRECTION.UP);
    }

    private void move(DIRECTION direction) {
        int[][] beforeMove = setArray(field);
        switch (direction) {
            case UP: {
                for (int x = 0; x < countCellsX; x++) {
                    int[] newArray = new int[countCellsY];
                    int index = 0;
                    for (int y = 0; y < countCellsY; y++) {
                        if (field[x][y] != 0) {
                            newArray[index] = field[x][y];
                            index++;
                        }
                    }
                    for (int i = index; i < countCellsY; i++) newArray[i] = 0;
                    setColumn(x, ifNeedSum(newArray, direction));
                }
                break;
            }
            case LEFT: {
                for (int y = 0; y < countCellsY; y++) {
                    int[] newArray = new int[countCellsX];
                    int index = 0;
                    for (int x = 0; x < countCellsX; x++) {
                        if (field[x][y] != 0) {
                            newArray[index] = field[x][y];
                            index++;
                        }
                    }
                    for (int i = index; i < countCellsX; i++) newArray[i] = 0;
                    setLine(y, ifNeedSum(newArray, direction));
                }
                break;
            }
            case RIGHT: {
                for (int y = 0; y < countCellsY; y++) {
                    int[] newArray = new int[countCellsX];
                    int index = newArray.length - 1;
                    for (int x = newArray.length - 1; x >= 0; x--) {
                        if (field[x][y] != 0) {
                            newArray[index] = field[x][y];
                            index--;
                        }
                    }
                    for (int i = index; i >= 0; i--) newArray[i] = 0;
                    setLine(y, ifNeedSum(newArray, direction));
                }
                break;
            }
            case DOWN: {
                for (int x = 0; x < countCellsX; x++) {
                    int[] newArray = new int[countCellsY];
                    int index = newArray.length - 1;
                    for (int y = newArray.length - 1; y >= 0; y--) {
                        if (field[x][y] != 0) {
                            newArray[index] = field[x][y];
                            index--;
                        }
                    }
                    for (int i = index; i >= 0; i--) newArray[i] = 0;
                    setColumn(x, ifNeedSum(newArray, direction));
                }
                break;
            }
        }
        if (isMoved(beforeMove, field)) createNewCell();
    }

    private int[] ifNeedSum(int[] array, DIRECTION direction) {
        int length = array.length;

        switch (direction) { //если соседние равны, то делаем манипуляции, потом будут нули в массиве
            case UP :
            case LEFT : {
                for (int i = 0; i < length - 1; i++) {
                    if (array[i] == array[i + 1]) {
                        array[i] = array[i] * 2;
                        array[i + 1] = 0;
                        i++;
                    }
                }
                break;
            }

            case DOWN :
            case RIGHT : {
                for (int i = length - 1; i > 0; i--) {
                    if (array[i] == array[i - 1]) {
                        array[i] = array[i] * 2;
                        array[i - 1] = 0;
                        i--;
                    }
                }
                break;
            }
        }
        //теперь убираю нули из массива
        int[] newArray = new int[length];
        switch (direction) {
            case UP:
            case LEFT: {
                int index = 0;
                for (int value : array) {
                    if (value != 0) {
                        newArray[index] = value;
                        index++;
                    }
                }
                for (int i = index; i < length; i++) newArray[i] = 0;
                break;
            }

            case DOWN:
            case RIGHT: {
                int index = length - 1;
                for (int i = index; i >= 0; i--) {
                    if (array[i] != 0) {
                        newArray[index] = array[i];
                        index--;
                    }
                }
                for (int i = index; i == 0; i--) newArray[i] = 0;
                break;
            }
        }
        return newArray;
    }

    private void setLine(int y, int[] line) {
        for (int x = 0; x < countCellsX; x++) {
            field[x][y] = line[x];
        }
    }

    private void setColumn(int x, int[] column) {
        if (countCellsY >= 0) System.arraycopy(column, 0, field[x], 0, countCellsY);
    }

    private int[][] setArray(int[][] array) {
        int[][] newArray = new int[countCellsX][countCellsY];
        for (int x = 0; x < countCellsX; x++) {
            System.arraycopy(array[x], 0, newArray[x], 0, countCellsY);
        }
        return newArray;
    }

    private boolean isMoved(int[][] before, int[][] after) { //было ли передвижение, чтобы создавать новую ячейку
        for (int x = 0; x < countCellsX; x++) {
            for (int y = 0; y < countCellsY; y++) {
                if (before[x][y] != after[x][y]) return true;
            }
        }
        return false;
    }

    public boolean isWinEndOfGame() {
        //проверяю все ячейки на наличие выигрышной
        for (int x = 0; x < countCellsX; x++) {
            for (int y = 0; y < countCellsY; y++) {
                if (field[x][y] == winCase) return true;
            }
        }
        return false;
    }

    private boolean isThereEmptyCells() {
        //проверяю все ячейки на наличие пустой/свободной
        for (int x = 0; x < countCellsX; x++) {
            for (int y = 0; y < countCellsY; y++) {
                if (field[x][y] == 0) return true;
            }
        }
        return false;
    }

    public boolean isThereMoves() {
        if (isThereEmptyCells()) return true;
        for (int y = 0; y < countCellsY; y++) { //прохожусь по строкам
            for (int x = 0; x < countCellsX - 1; x++) if (getCell(x, y) == getCell(x + 1, y) || getCell(x, y) == 0) return true;
        }

        for (int x = 0; x < countCellsX; x++) { //прохожусь по столбцам
            for (int y = 0; y < countCellsY - 1; y++) if (getCell(x, y) == getCell(x, y + 1)) return true;
        }
        return false;
    }

    int[][] getField() {
        return field;
    }

    public int getCell(int x, int y) {
        return field[x][y];
    }

    public int getCountCellsX() {
        return countCellsX;
    }

    public int getCountCellsY() {
        return countCellsY;
    }

    public int getWinCase() {
        return winCase;
    }

}
