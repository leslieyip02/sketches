// sorting algorithm visualiser

int graphWidth = 2000;
int maxBarHeight = 1000;

// since processing doesn't work well with packages,
// the sorting algorithms in the algorithms package
// store the sorting states in a csv to be read by this sketch
String path = "./stateData.csv";
Table stateData;

int numStates, stateIndex;
boolean needToSwap, sorted;

int numBars;
Bar[] bars;

// delay in ms
int delay = 500;

int defaultFill = #ffffff;
int selectedFill = #ff0000;
int swappedWithFill = #9a0000;
int sortedFill = #00ff00;

void setup() {
    fullScreen();

    stateData = loadTable(path, "csv");
    System.out.println(String.format("Loaded table with %d rows and %d columns.", 
        stateData.getRowCount(), stateData.getColumnCount()));

    numStates = stateData.getRowCount();
    numBars = stateData.getColumnCount() - 2;
    bars = new Bar[numBars];

    int maxValue = stateData.getInt(0, 0);
    for (int i = 1; i < numBars; i++)
        if (stateData.getInt(0, i) > maxValue)
            maxValue = stateData.getInt(0, i);

    float x0 = (width - graphWidth) / 2;
    float y0 = (height - maxBarHeight) / 3 + maxBarHeight;
    float barWidth = graphWidth / numBars;
    float heightRatio = maxBarHeight / maxValue;
    
    for (int i = 0; i < numBars; i++) {
        int value = stateData.getInt(0, i);
        float height = value * heightRatio;
        bars[i] = new Bar(value, x0 + i * barWidth, y0, barWidth, height);
    }

    stateIndex = 0;
    needToSwap = false;
    sorted = false;
}

void update() {
    for (Bar bar : bars)
        bar.fill = defaultFill;

    int index = stateData.getInt(stateIndex, numBars);
    int swappedWith = stateData.getInt(stateIndex, numBars + 1);
    bars[index].fill = selectedFill;
    
    if (needToSwap) {
        bars[index].drawArrowTo(bars[swappedWith]);
        bars[index].fill = swappedWithFill;
        bars[swappedWith].fill = selectedFill;
        needToSwap = false;
    } else if (swappedWith != -1) {
        // make the swap in the next draw() call
        // so that there is time to see the swap
        needToSwap = true;
        return;
    }

    // only update the values once swapped
    for (int i = 0; i < numBars; i++)
        bars[i].setValue(stateData.getInt(stateIndex, i));

    stateIndex++;
}

void draw() {
    if (stateIndex < numStates) {
        background(#FFFFFF);
        update();
        delay(delay);
        
        for (Bar bar : bars)
            bar.drawBar();
    } else if (!sorted) {
        // final state of the sketch is the sorted bar graph
        background(#FFFFFF);
        delay(delay);

        for (Bar bar : bars) {
            bar.fill = sortedFill;
            bar.drawBar();
        }

        sorted = true;
    }
}