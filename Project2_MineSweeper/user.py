from field import Field
from sweeper import Sweeper

class User:
    def __init__(self):
        """build a field"""
        self.create_field()
        self.final_field = self.final_field()

    def create_field(self):
        """build a field and get info. from the field"""
        self.field   = Field()
        self.length  = self.field.size
        self.width   = self.field.size
        self.mine_no = self.field.mine_no         # get number of mines from the field

    def final_field(self):
        field = [[9 for col in range(self.length)] for row in range(self.width)]

        return field

    def init_sweeper(self):
        """init a sweeper"""
        sweeper = Sweeper()

# begin to sweep mine
user    = User()
sweeper = Sweeper(user.length, user.width)         # !!!!!!!!!!don't input the mine number to the sweeper

for i in range(user.length):
    for j in range(user.width):
        print("%2d"%(user.field.field[i][j]), end=' ')
    print()

print()

while True:
    # return the cell sweeper want to reveal
    (row, col) = sweeper.running()
    if -1 == row:
        print('Success')
        break
    # check the cell info. from field
    state = user.field.get_cell_info(row, col)
    print('{}, {} state{}'.format(row, col, state))

    # sweeper click a mine and game is over
    if 9 == state:
        print('BOOOOOOOM!!!!GG!!!!!!!!')
        break
    #
    else:
        user.final_field[row][col] = 0
        sweeper.user_update(row, col, state)

for i in range(user.length):
    for j in range(user.width):
        print("%2d"%(user.field.field[i][j]), end=' ')
    print()

print()

for i in range(user.length):
    for j in range(user.width):
        print("%2d"%(user.final_field[i][j]), end=' ')
    print()



