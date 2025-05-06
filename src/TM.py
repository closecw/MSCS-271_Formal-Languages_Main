"""
Python program for a Turing Machine.
Will accept any string in the language {1^n0^n | n is a positive integer}.
Inspired by code from the book "Formal Language: A Practical Introduction" by Adam Brooks Webber.
Code referenced from Dr. Seth Dutter, fully written by me.
@author <Carter Close>
@version 1.0
"""

state_diagram = {
    #state, read: write, direction, next_state
    (1, 'B'): ('B', 'R', 5),
    (1, '1'): ('B', 'R', 2),
    (2, '1'): ('1', 'R', 2),
    (2, '0'): ('0', 'R', 2),
    (2, 'B'): ('B', 'L', 3),
    (3, '0'): ('B', 'L', 4),
    (4, '0'): ('0', 'L', 4),
    (4, '1'): ('1', 'L', 4),
    (4, 'B'): ('B', 'R', 1)
}

class Node(object):
    """
    Class for a node in the TM. Contains methods to move left and right, and to return a character of a node.
    """
    def __init__(self, left=None, right=None, character=None):
        """
        Constructor for the Node class.
        :param left: Left pointer.
        :param right: Right pointer.
        :param character: Value of the node.
        """
        self.left = left
        self.right = right
        self.character = character

    def move_right(self, character=None):
        """
        Function to move right, if nothing exists on the right, create a new node with the current character.
        :param character: Character of the existing node.
        :return: Node to the right of the current node.
        """
        if character is not None:
            self.character = character
        if self.right is None:
            self.right = Node(left=self)
        return self.right

    def move_left(self, character=None):
        """
        Function to move left, if nothing exists on the left, create a new node with the current character.
        :param character: Character of the existing node.
        :return: Node to the left of the current node.
        """
        if character is not None:
            self.character = character
        if self.left is None:
            self.left = Node(right=self)
        return self.left

    def get_character(self):
        return self.character if self.character is not None else 'B'

class TuringMachine(object):
    """
    Class for a TM itself. Contains methods to run the TM and to display the tape of the TM.
    """
    def __init__(self, string, state_diagram, start_state=1, accept_state=5):
        """
        Constructor for the TM class. Also initializes the tape using a doubly linked list.
        :param string: Input string.
        :param state_diagram: State diagram given above.
        :param start_state: Start state of the TM.
        :param accept_state: Accept state of the TM.
        """
        self.accept_state = accept_state
        self.current_state = start_state
        self.state_diagram = state_diagram
        self.tape = Node(character=string[0])
        current = self.tape
        for character in string[1:]:
            current.right = Node(left=current, character=character)
            current = current.right

    def run_machine(self):
        """
        Method to run the TM. Prints the tape, what it reads, writes, and moves to.
        :return: True if accepted, False if rejected.
        """
        key = (self.current_state, self.tape.get_character())
        while key in self.state_diagram:
            self.display_tape()
            write, direction, next_state = self.state_diagram[key]
            print(f'{self.tape.get_character()}/{write}, {direction}')
            self.tape.character = write
            if direction == 'R':
                self.tape = self.tape.move_right()
            else:
                self.tape = self.tape.move_left()
            self.current_state = next_state
            key = (self.current_state, self.tape.get_character())
        self.display_tape()
        return self.current_state == self.accept_state

    def display_tape(self):
        """
        Helper method to display the tape.
        :return: String representation of the tape.
        """
        curr = self.tape
        while curr.left:
            curr = curr.left
        tape_string = ''
        pointer = curr
        while pointer:
            if pointer == self.tape:
                tape_string += f'[{pointer.get_character()}]'   # Surround current character with brackets
            else:
                tape_string += f'{pointer.get_character()}'     # Print rest normally
            pointer = pointer.right
        print(tape_string)

if __name__ == '__main__':
    string = input('Enter a string: ')
    tm = TuringMachine(string, state_diagram)
    if tm.run_machine():
        print('Accepted')
    else:
        print('Rejected')