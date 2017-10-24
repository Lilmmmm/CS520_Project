from random import randint


class Field:
    """A square grid landscape that contains mines randomly"""
    def __init__(self, size = 30, ratio = 0.15):
        self.size      = size                          # the scale of field
        self.ratio     = ratio                         # the proportion of mines in the field
        self.mine_no   = int(self.size ** 2 * self.ratio)   # Numbers of mines in the field
        print(self.mine_no)
        self.field     = self.build_field()            # build a origin field
        self.cell_info = self.cell_info()              # initial each cell
        self.lay_mine()                                # lay mines
        self.record_field()                            # update each cell info.

    def build_field(self):
        """build a two-dimensional array to indicate the field, 0 means clear"""
        field = [[0 for col in range(self.size)] for row in range(self.size)]

        return field

    def cell_info(self):
        """create a dictionary to record each cell's info."""
        info = dict()
        for row in range(self.size):
            for col in range(self.size):
                info[(row, col)] = 0                    # indicate number of mines around the cell, 9 means mine

        return info

    def lay_mine(self):
        """lay certain number of mines in random cells"""
        for cnt in range(self.mine_no):
            # random pick a cell to lay mine
            while True:
                row = randint(0, self.size - 1)
                col = randint(0, self.size - 1)

                if 9 != self.cell_info[(row, col)]:
                    if (0 <= (row - 1)) and (0 <= (col - 1)):
                        if 9 != self.cell_info[(row - 1, col - 1)]:
                            break
                    if 0 <= (row - 1):
                        if 9 != self.cell_info[(row - 1, col)]:
                            break
                    if (0 <= (row - 1)) and (self.size > (col + 1)):
                        if 9 != self.cell_info[(row - 1, col + 1)]:
                            break
                    if 0 <= (col - 1):
                        if 9 != self.cell_info[(row, col - 1)]:
                            break
                    if self.size > (col + 1):
                        if 9 != self.cell_info[(row, col + 1)]:
                            break
                    if (self.size > (row + 1)) and (0 <= ( col - 1)):
                        if 9 != self.cell_info[(row + 1, col - 1)]:
                            break
                    if self.size > (row + 1):
                        if 9 != self.cell_info[(row + 1, col)]:
                            break
                    if (self.size > (row + 1)) and (self.size > (col + 1)):
                        if 9 != self.cell_info[(row + 1, col + 1)]:
                            break
            # pass all check and lay a mine
            self.cell_info[(row, col)] = 9

    def record_field(self):
        """save all info. of all cells in the field"""
        for row in range(self.size):
            for col in range(self.size):
                # exam whether a cell is a mine
                if 9 != self.cell_info[(row, col)]:
                    # calculate number of mines around the cell
                    # check existence of other cell
                    if (0 <= (row - 1)) and (0 <= (col - 1)):
                        if 9 == self.cell_info[(row - 1, col - 1)]:
                            self.cell_info[(row, col)] += 1
                    if 0 <= (row - 1):
                        if 9 == self.cell_info[(row - 1, col)]:
                            self.cell_info[(row, col)] += 1
                    if (0 <= (row - 1)) and (self.size > (col + 1)):
                        if 9 == self.cell_info[(row - 1, col + 1)]:
                            self.cell_info[(row, col)] += 1
                    if 0 <= (col - 1):
                        if 9 == self.cell_info[(row, col - 1)]:
                            self.cell_info[(row, col)] += 1
                    if self.size > (col + 1):
                        if 9 == self.cell_info[(row, col + 1)]:
                            self.cell_info[(row, col)] += 1
                    if (self.size > (row + 1)) and (0 <= ( col - 1)):
                        if 9 == self.cell_info[(row + 1, col - 1)]:
                            self.cell_info[(row, col)] += 1
                    if self.size > (row + 1):
                        if 9 == self.cell_info[(row + 1, col)]:
                            self.cell_info[(row, col)] += 1
                    if (self.size > (row + 1)) and (self.size > (col + 1)):
                        if 9 == self.cell_info[(row + 1, col + 1)]:
                            self.cell_info[(row, col)] += 1

        # transfer info. to the field
        for row in range(self.size):
            for col in range(self.size):
                if 9 == self.cell_info[(row, col)]:
                    self.field[row][col] = 9               # 9 means a mine
                else:
                    self.field[row][col] = self.cell_info[(row, col)]   # number of mines around

    def change_size(self, size):
        """user can customize the size of field"""
        self.size = size

    def change_ratio(self, ratio):
        """user can customize the proportion of mines in the field"""
        self.ratio = ratio

    def get_cell_info(self, row, col):
        return self.cell_info[(row, col)]


# mmmmmm = Field()
# for i in range(mmmmmm.size):
#     for j in range(mmmmmm.size):
#         print("%2d"%(mmmmmm.field[i][j]), end=' ')
#     print()



