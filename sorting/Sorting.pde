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
int multipleSelectedFill = #ffff00;
int swappedWithFill = #9a0000;
int sortedFill = #00ff00;

void setup() {
    fullScreen();

    if (args != null) {
        String flag = trim(args[0]);

        if (flag.equals("-s")) {
            delay = 0;
        } else if (flag.equals("-d")) {
            delay = int(args[1]);
        }
    }

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
        bars[i] = new Bar(value, maxValue, 
            x0 + i * barWidth, y0, barWidth, height, maxBarHeight);
    }

    stateIndex = 0;
    needToSwap = false;
    sorted = false;
}

void keyPressed() {
    if (key == ENTER)
        exit();
}

void update() {
    for (Bar bar : bars)
        bar.fill = defaultFill;

    String[] indexEntries = split(stateData.getString(stateIndex, numBars), ",");
    
    // single selection
    if (indexEntries.length == 1) {
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

    // multiple selection
    } else {
        for (int i = 0; i < indexEntries.length; i++) {
            int index = int(indexEntries[i]);
            bars[index].fill = multipleSelectedFill;
        }
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

        // if delay is 0, then need to save as gif
        if (delay == 0)
            saveFrame();

    } else if (!sorted) {
        // final state of the sketch is the sorted bar graph
        background(#FFFFFF);
        delay(delay);

        for (Bar bar : bars)
            bar.drawBar(sortedFill);

        sorted = true;

        if (delay == 0) {
            saveFrame();
            exit();
        }
    }
}