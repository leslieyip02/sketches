class Bar {
    int value;
    
    float x;
    float y;
    float w;
    float h;

    int fill = #ffffff;

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

    void drawArrowTo(Bar other) {
        float x0 = (this.x + other.x + other.w) / 2;
        float y0 = this.y + 32;
        float d = other.x - this.x;
        arc(x0, y0, d, d, 0, PI);

        float x1 = other.x + other.w / 2;
        float y1 = y0;
        float x2 = x1 + 12;
        float y2 = y0 + 12;
        float x3 = x1 - 12;
        float y3 = y0 + 12;
        fill(0, 0, 0);
        triangle(x1, y1, x2, y2, x3, y3);
    }

    void drawBar() {
        stroke(#000000);
        
        fill(#000000);
        textSize(32);
        textAlign(CENTER);
        text(this.value, this.x + this.w / 2, this.y - this.h - 32);

        fill(this.fill);
        rect(this.x, this.y - this.h, this.w, this.h);
    }
}