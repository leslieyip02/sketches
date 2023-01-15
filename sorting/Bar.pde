class Bar {
    int value;
    
    float x;
    float y;
    float w;
    float h;
    boolean selected;

    Bar(int value, float x, float y, float w, float h) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void setValue(int value) {
        // height ratio to max height is preserved on change
        this.h = value * this.h / this.value;
        this.value = value;
    }

    void draw() {
        stroke(0, 0, 0);
        
        if (this.selected)
            fill(255, 0, 0);
        else
            fill(255, 255, 255);

        rect(this.x, this.y, this.w, -this.h);
    }
}