// forward decl
int torusMod(int val, int max);
int getCell(int width, int height, int torus, int row, int col);
int transform(int living, int state);

__kernel void calcNextGeneration(__global int *inCells,
                                    __global int *outCells,
                                    const int2 size,
                                    const int isTorus,
                                    const int isMoore) {
    int y = get_global_id(0);
    int x = get_global_id(1);
    int width = size[0];
    int height = size[1];
    int index = x + y * width;
    int mod = (isMoore == 1)?-1:0;
    int sz = (mod == -1)?8:4;
    int neig[sz];
    int ctr = 0;
    int target;
    int state = inCells[index];

    for(int i = 0; i < 9; i++) {
        if(i % 2 > mod && i != 4) {
            int fY = i % 3;
            int fX = i / 3;
            target = getIndex(width, height, isTorus, x-1+fX, y-1+fY);
            if(target != -1) {
                if(inCells[target] == 1) {
                    ctr++;
                }
            }
        }
    }
    target = getIndex(width, height, isTorus, x, y);
    if(target != -1) {
        outCells[index] = transform(ctr, inCells[target]);
    }
    //outCells[index] = !state;

    // getCell(&inCells, width, height, isTorus, x+1, y+1)
}

// transform
int transform(int living, int state) {
    if(state == 0) {
        if(living == 3) {
            return 1;
        }
    } else {
        if(living == 3 || living == 2) {
            return 1;
        } else {
            return 0;
        }
    }
    return state;
}

// helper
int torusMod(int val, int max) {
    return ((val > 0)?val:max+val) % max;
}

int getIndex(int width, int height, int torus, int row, int col) {
    if(torus == 1) {
        return torusMod(row, width) + (torusMod(col, height) * width);
    } else if(row < width && row >= 0 && col < height && col >= 0) {
        return row + (col*width);
    }
    return -1;
}