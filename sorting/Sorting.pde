int graphWidth = 1000;
int maxBarHeight = 100;
Bar[] bars;

void setup() {
    size(1280, 960);
    fullScreen();

    String path = "./stateData.csv";
    Table stateData = loadTable(path, "csv");

    System.out.println(String.format("Loaded table with %d rows and %d columns.", 
        stateData.getRowCount(), stateData.getColumnCount()));

    int numBars = stateData.getColumnCount() - 2;
    int maxValue = stateData.getInt(0, 0);
    for (int i = 1; i < numBars; i++)
        if (stateData.getInt(0, i) > maxValue)
            maxValue = stateData.getInt(0, i);

    float barWidth = graphWidth / numBars;
    float heightRatio = maxBarHeight / maxValue;

    bars = new Bar[numBars];
    for (int i = 0; i < numBars; i++) {
        int value = stateData.getInt(0, i);
        bars[i] = new Bar(value, i * barWidth, 100.0, barWidth, value * heightRatio);
    }
}

void draw() {
    background(#FFFFFF);

    for (Bar bar : bars)
        bar.draw();
}