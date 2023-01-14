void setup() {
    size(1280, 960);
    fullScreen();

    String path = "./stateData.csv";
    Table stateData = loadTable(path, "csv");

    System.out.println(String.format("Loaded table with %d rows and %d columns.", 
        stateData.getRowCount(), stateData.getColumnCount()));
}

void draw() {
    background(#FFFFFF);
}