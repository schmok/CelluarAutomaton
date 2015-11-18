__kernel void calcNextGeneration(__global int2 *inCells,
                                    __global int2 *outCells,
                                    const int2 size) {
    int cX = get_global_id(0);
    int cY = get_global_id(1);
    if(inCells[cX][cY] == 1) {
        outCells[cX][cY] = 0;
    } else {
        outCells[cX][cY] = 1;
    }
}