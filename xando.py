import tkinter as tk
from tkinter import messagebox

class TicTacToeGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("🎮 Tic-Tac-Toe")

        self.current_player = "X"
        self.board = [["" for _ in range(3)] for _ in range(3)]

        self.status_label = tk.Label(root, text="Player X's turn", font=('Arial', 16, 'bold'))
        self.status_label.grid(row=0, column=0, columnspan=3, pady=10)

        self.buttons = [[None for _ in range(3)] for _ in range(3)]
        self.create_board()

    def create_board(self):
        for row in range(3):
            for col in range(3):
                btn = tk.Button(
                    self.root,
                    text="",
                    font=('Arial', 24, 'bold'),
                    width=5,
                    height=2,
                    bg="#f0f0f0",
                    command=lambda r=row, c=col: self.on_click(r, c)
                )
                btn.grid(row=row+1, column=col, padx=5, pady=5)
                self.buttons[row][col] = btn

    def on_click(self, row, col):
        if self.board[row][col] == "":
            self.board[row][col] = self.current_player
            self.buttons[row][col].config(
                text=self.current_player,
                fg='red' if self.current_player == "X" else 'green'
            )

            if self.check_win(self.current_player):
                messagebox.showinfo("Game Over", f"🎉 Player {self.current_player} wins!")
                self.reset_game()
                return

            if self.is_full():
                messagebox.showinfo("Game Over", "It's a tie!")
                self.reset_game()
                return

            self.current_player = "O" if self.current_player == "X" else "X"
            self.status_label.config(text=f"Player {self.current_player}'s turn")

    def check_win(self, player):
        # Check rows and columns
        for i in range(3):
            if all(self.board[i][j] == player for j in range(3)) or \
               all(self.board[j][i] == player for j in range(3)):
                return True
        # Check diagonals
        if all(self.board[i][i] == player for i in range(3)) or \
           all(self.board[i][2 - i] == player for i in range(3)):
            return True
        return False

    def is_full(self):
        return all(self.board[row][col] != "" for row in range(3) for col in range(3))

    def reset_game(self):
        self.current_player = "X"
        self.board = [["" for _ in range(3)] for _ in range(3)]
        self.status_label.config(text="Player X's turn")
        for row in range(3):
            for col in range(3):
                self.buttons[row][col].config(text="", bg="#f0f0f0")

# Run the game
if __name__ == "__main__":
    root = tk.Tk()
    app = TicTacToeGUI(root)
    root.mainloop()
