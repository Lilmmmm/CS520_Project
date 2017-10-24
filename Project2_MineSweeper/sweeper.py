from random import randint

class Sweeper:
    def __init__(self, length, width, mines = None):
        """initial all the info of sweeper and unknown field"""
        self.length          = length               # the length of the field
        self.width           = width                # the width of the field
        self.cell_info       = self.cell_info()     # two-dimension dict to record cell info.
        self.cell_visted     = set()                # a set used to record cell uncovered
        self.cell_unvisited  = self.init_unvisited()# a set used to record cell covered
        self.cell_safe       = set()                # a set used to record cell that not mine definitely
        self.add_neighbor()                         # add neighbors info. to each cell
        self.add_detect_area()                      # add detect area info. to each cell

    def cell_info(self):
        """maintain info. of all cells for the sweeper"""
        info = dict()
        for row in range(self.length):
            for col in range(self.width):
                info[(row, col)] = dict()                           # use a dict to store cell info

                info[(row, col)]['IsMine']            = False         # whether this cell is a mine
                info[(row, col)]['UnvisitedNeighbor'] = set()         # neighbor cells around this cell
                info[(row, col)]['Neighbor']          = set()         # neighbor cells around this cell
                info[(row, col)]['DetectedMineNo']    = 0             # detected mines numbers around this cell
                info[(row, col)]['UndetectedMineNo']  = 0             # undetected mines numbers around this cell
                info[(row, col)]['CellState']         = -1            # cell state get from user
                info[(row, col)]['DetectArea']        = set()         # the area that could be detected from the cell

        return info

    def init_unvisited(self):
        """add all uncovered cells into the set"""
        unvisited = set()
        for row in range(self.length):
            for col in range(self.width):
                unvisited.add((row, col))

        return unvisited

    def add_neighbor(self):
        """add unvisited neighbor to the cell"""
        for row in range(self.length):
            for col in range(self.width):
                if (0 <= (row - 1)) and (0 <= (col - 1)):
                    self.cell_info[(row, col)]['Neighbor'].add((row - 1, col - 1))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row - 1, col - 1))
                if 0 <= (row - 1):
                    self.cell_info[(row, col)]['Neighbor'].add((row - 1, col))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row - 1, col))
                if (0 <= (row - 1)) and (self.width > (col + 1)):
                    self.cell_info[(row, col)]['Neighbor'].add((row - 1, col + 1))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row - 1, col + 1))
                if 0 <= (col - 1):
                    self.cell_info[(row, col)]['Neighbor'].add((row, col - 1))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row, col - 1))
                if self.width > (col + 1):
                    self.cell_info[(row, col)]['Neighbor'].add((row, col + 1))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row, col + 1))
                if (self.length > (row + 1)) and (0 <= (col - 1)):
                    self.cell_info[(row, col)]['Neighbor'].add((row + 1, col - 1))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row + 1, col - 1))
                if self.length > (row + 1):
                    self.cell_info[(row, col)]['Neighbor'].add((row + 1, col))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row + 1, col))
                if (self.length > (row + 1)) and (self.width > (col + 1)):
                    self.cell_info[(row, col)]['Neighbor'].add((row + 1, col + 1))
                    self.cell_info[(row, col)]['UnvisitedNeighbor'].add((row + 1, col + 1))

    def add_detect_area(self):
        """add detect area to the cell, 5 * 5 area"""
        for row in range(self.length):
            for col in range(self.width):
                if (0 <= (row - 2)) and (0 <= (col - 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 2, col - 2))
                if (0 <= (row - 2)) and (0 <= (col - 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 2, col - 1))
                if 0 <= (row - 2):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 2, col))
                if (0 <= (row - 2)) and (self.width > (col + 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 2, col + 1))
                if (0 <= (row - 2)) and (self.width > (col + 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 2, col + 2))

                if (0 <= (row - 1)) and (0 <= (col - 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 1, col - 2))
                if (0 <= (row - 1)) and (0 <= (col - 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 1, col - 1))
                if 0 <= (row - 1):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 1, col))
                if (0 <= (row - 1)) and (self.width > (col + 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 1, col + 1))
                if (0 <= (row - 1)) and (self.width > (col + 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row - 1, col + 2))

                if 0 <= (col - 2):
                    self.cell_info[(row, col)]['DetectArea'].add((row, col - 2))
                if 0 <= (col - 1):
                    self.cell_info[(row, col)]['DetectArea'].add((row, col - 1))
                if self.width > (col + 1):
                    self.cell_info[(row, col)]['DetectArea'].add((row, col + 1))
                if self.width > (col + 2):
                    self.cell_info[(row, col)]['DetectArea'].add((row, col + 2))

                if (self.length > (row + 1)) and (0 <= (col - 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 1, col - 2))
                if (self.length > (row + 1)) and (0 <= (col - 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 1, col - 1))
                if self.length > (row + 1):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 1, col))
                if (self.length > (row + 1)) and (self.width > (col + 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 1, col + 1))
                if (self.length > (row + 1)) and (self.width > (col + 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 1, col + 2))

                if (self.length > (row + 2)) and (0 <= (col - 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 2, col - 2))
                if (self.length > (row + 2)) and (0 <= (col - 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 2, col - 1))
                if self.length > (row + 2):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 2, col))
                if (self.length > (row + 2)) and (self.width > (col + 1)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 2, col + 1))
                if (self.length > (row + 2)) and (self.width > (col + 2)):
                    self.cell_info[(row, col)]['DetectArea'].add((row + 2, col + 2))

    def unvisited_to_visited(self, row, col):
        """move a cell from unvisited set to visited set"""
        self.cell_unvisited.discard((row, col))
        self.cell_visted.add((row, col))

    def random_click(self):
        """choose a random cell to click"""
        while True:
            row = randint(0, self.length - 1)
            col = randint(0, self.width - 1)
            print('in the while')
            # check whether the cell is unvisited
            if (row, col) in self.cell_unvisited:
                return (row, col)

    def mine_update(self, row, col):
        """a mine is marked and update its neighbor"""
        for (i, j) in self.cell_info[(row, col)]['Neighbor']:
            # if mine is in the unvisited set, then discard
            if (row, col) in self.cell_info[(i, j)]['UnvisitedNeighbor']:
                self.cell_info[(i, j)]['UnvisitedNeighbor'].discard((row, col))
                self.cell_info[(i, j)]['DetectedMineNo'] += 1

    def normal_update(self, row, col):
        """a cell is revealed and update its neighbor"""
        for (i, j) in self.cell_info[(row, col)]['Neighbor']:
            self.cell_info[(i, j)]['UnvisitedNeighbor'].discard((row, col))

    def user_update(self, row, col, state):
        """use info. from the user to update cell info."""
        self.cell_info[(row, col)]['CellState'] = state

        # if cell state is zero, add all its neighbors to safe set
        if 0 == state:
            for (i, j) in self.cell_info[(row, col)]['UnvisitedNeighbor']:
                # # if cell is visited, don't add
                # if (i, j) in self.cell_visted:
                #     continue

                # add to the safe set
                self.cell_safe.add((i, j))

            for (a, b) in self.cell_info[(row, col)]['Neighbor']:
                # discard from the unvisited neighbors
                self.cell_info[(a, b)]['UnvisitedNeighbor'].discard((row, col))

            return

        # # if unvisited number around cell is equal to state minus mine , all unvisited neighbor are mines
        # if state == (len(self.cell_info[(row, col)]['UnvisitedNeighbor']) + \
        #              self.cell_info[(row, col)]['DetectedMineNo']):
        #     # i, j indicate neighbors of this cell
        #     for (i, j) in self.cell_info[(row, col)]['UnvisitedNeighbor']:
        #         self.unvisited_to_visited(i, j)
        #         self.cell_info[(i, j)]['IsMine'] = True
        #     # walk the dict to update all mines info.
        #     for (x, y) in self.cell_info:
        #         if self.cell_info[(x, y)]['IsMine']:
        #             self.mine_update(x, y)
        else:
            self.normal_update(row, col)

    def advance_sweep(self):
        """when normal rules cannot go on, execute this sweep"""
        for (row, col) in self.cell_info:
            # if cell is a mine, continue
            if self.cell_info[(row, col)]['IsMine']:
                continue
            # if cell state is 0, continue
            if 0 == self.cell_info[(row, col)]['CellState']:
                continue
            # if cell is unvisited, continue
            if (row, col) in self.cell_unvisited:
                continue

            for (i, j) in self.cell_info[(row, col)]['DetectArea']:
                # if cell is a mine, continue
                if self.cell_info[(i, j)]['IsMine']:
                    continue
                # if cell state is 0, continue
                if 0 == self.cell_info[(i, j)]['CellState']:
                    continue
                # if cell is unvisited, continue
                if (i, j) in self.cell_unvisited:
                    continue

                if False == self.cell_info[(row, col)]['UnvisitedNeighbor'].issuperset(
                        self.cell_info[(i, j)]['UnvisitedNeighbor']):
                    if False == self.cell_info[(i, j)]['UnvisitedNeighbor'].issuperset(
                            self.cell_info[(row, col)]['UnvisitedNeighbor']):
                        if True == self.cell_info[(row, col)]['UnvisitedNeighbor'].isdisjoint(
                                self.cell_info[(i, j)]['UnvisitedNeighbor']):
                            if (self.cell_info[(row, col)]['CellState'] - self.cell_info[(row, col)]['DetectedMineNo']) > (
                                self.cell_info[(i, j)]['CellState'] - self.cell_info[(i, j)]['DetectedMineNo']):
                                if len(self.cell_info[(row, col)]['UnvisitedNeighbor'] - self.cell_info[(i, j)]['UnvisitedNeighbor']) == (
                                    (self.cell_info[(row, col)]['CellState'] - self.cell_info[(row, col)]['DetectedMineNo']) -
                                    (self.cell_info[(i, j)]['CellState'] - self.cell_info[(i, j)]['DetectedMineNo'])):

                                    difference = self.cell_info[(row, col)]['UnvisitedNeighbor'] - \
                                                 self.cell_info[(i, j)]['UnvisitedNeighbor']

                                    for (a, b) in difference:
                                        self.unvisited_to_visited(a, b)
                                        self.cell_info[(a, b)]['IsMine'] = True
                                        print('66666666666666666666666666: {}, {}'.format(a, b))

                                        # walk the dict to update all mines info.
                                    for (x, y) in self.cell_info:
                                        if self.cell_info[(x, y)]['IsMine']:
                                            self.mine_update(x, y)
                                    print('66666666666666666666666666666')
                                    return -1

                if True == self.cell_info[(row, col)]['UnvisitedNeighbor'].issuperset(self.cell_info[(i, j)]['UnvisitedNeighbor']):
                    if (self.cell_info[(row, col)]['CellState'] - self.cell_info[(row, col)]['DetectedMineNo']) == (
                        self.cell_info[(i, j)]['CellState'] - self.cell_info[(i, j)]['DetectedMineNo']):

                        safe_area = self.cell_info[(row, col)]['UnvisitedNeighbor'] - \
                                    self.cell_info[(i, j)]['UnvisitedNeighbor']

                        if 0 == len(safe_area):
                            continue

                        for (c, d) in safe_area:
                            self.unvisited_to_visited(c, d)
                            self.cell_safe.add((c, d))
                        print('99999999999999999999999999999999999')
                        return -2
        return 0


    def running(self):
        """begin to sweep mines"""
        # if no cell is revealed, random click one
        if 0 == len(self.cell_visted):
            (i, j) = self.random_click()
            self.unvisited_to_visited(i, j)
            print('{}, {}'.format(i, j))
            return (i, j)

        # if safe set is not empty, choose a random safe cell to click
        if 0 != len(self.cell_safe):
            (i, j) = self.cell_safe.pop()
            self.unvisited_to_visited(i, j)
            return (i, j)

        # walk the two-dimension dict
        change = True
        while change:
            change = False

            for (row, col) in self.cell_info:
                # if cell is a mine, continue
                if self.cell_info[(row, col)]['IsMine']:
                    continue
                # if cell state is 0, continue
                if 0 == self.cell_info[(row, col)]['CellState']:
                    continue
                # if cell is unvisited, continue
                if (row, col) in self.cell_unvisited:
                    continue

                # if number of unvisited neighbors add detected mines is equal to cell state, all neighbor are mine
                if self.cell_info[(row, col)]['CellState'] == (
                   len(self.cell_info[(row, col)]['UnvisitedNeighbor']) +
                   self.cell_info[(row, col)]['DetectedMineNo']):

                    # i, j indicate neighbors of this cell
                    for (i, j) in self.cell_info[(row, col)]['UnvisitedNeighbor']:
                        self.unvisited_to_visited(i, j)
                        self.cell_info[(i, j)]['IsMine'] = True
                        change = True
                    # walk the dict to update all mines info.
                    for (x, y) in self.cell_info:
                        if self.cell_info[(x, y)]['IsMine']:
                            self.mine_update(x, y)

                # if number of detected mines is equal to cell state, all un-vi neighbor are safe
                if self.cell_info[(row, col)]['DetectedMineNo'] == \
                   self.cell_info[(row, col)]['CellState']:

                    # all neighbors are visited
                    if 0 == len(self.cell_info[(row, col)]['UnvisitedNeighbor']):
                        continue

                    for (i, j) in self.cell_info[(row, col)]['UnvisitedNeighbor']:
                        self.unvisited_to_visited(i, j)
                        self.cell_safe.add((i, j))

                    # return safe set
                    if 0 != len(self.cell_safe):
                        (i, j) = self.cell_safe.pop()
                        print('safe {}, {}'.format(i, j))
                        return (i, j)

            if False == change:
                flag = self.advance_sweep()
                if -1 == flag:
                    change = True
                elif -2 == flag:
                    (e, f) = self.cell_safe.pop()
                    print('advance {}, {}'.format(e, f))
                    return (e, f)
                elif 0 == flag:
                    pass

        if 0 != len(self.cell_unvisited):
            (i, j) = self.cell_unvisited.pop()
            print('Unvisited {}, {}'.format(i, j))
            self.unvisited_to_visited(i, j)
            return (i, j)
        else:
            return (-1, -1)




