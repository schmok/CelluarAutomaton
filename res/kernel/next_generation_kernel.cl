__kernel void calcNextGeneration(__global int2 *inCells,
                                    __global int2 *outCells,
                                    const int2 size) {
    int cX = get_global_id(0);
    int cY = get_global_id(1);
}