"""
Crystal Long
Date: 2025-09-29
Assignment: Fans DB Viewer/Updater (Python + MySQL)
Purpose: Tkinter GUI with Display & Update buttons to view/update a 'fans' record by ID.
"""

import tkinter as tk
from tkinter import ttk, messagebox
from fans_db import FansRepository, DBConfig

# --- Helper / UI logic (kept simple & readable) ---

class FansApp(tk.Tk):
    def __init__(self, repo: FansRepository | None = None):
        super().__init__()
        self.title("Fans DB Viewer/Updater")
        self.geometry("420x260")

        self.repo = repo or FansRepository()

        # ID row
        self.label_id = ttk.Label(self, text="ID:")
        self.label_id.grid(row=0, column=0, padx=10, pady=10, sticky="e")
        self.entry_id = ttk.Entry(self, width=10)
        self.entry_id.grid(row=0, column=1, padx=10, pady=10, sticky="w")

        # Firstname
        self.label_first = ttk.Label(self, text="First Name:")
        self.label_first.grid(row=1, column=0, padx=10, pady=5, sticky="e")
        self.entry_first = ttk.Entry(self, width=30)
        self.entry_first.grid(row=1, column=1, padx=10, pady=5, sticky="w")

        # Lastname
        self.label_last = ttk.Label(self, text="Last Name:")
        self.label_last.grid(row=2, column=0, padx=10, pady=5, sticky="e")
        self.entry_last = ttk.Entry(self, width=30)
        self.entry_last.grid(row=2, column=1, padx=10, pady=5, sticky="w")

        # Favorite team
        self.label_team = ttk.Label(self, text="Favorite Team:")
        self.label_team.grid(row=3, column=0, padx=10, pady=5, sticky="e")
        self.entry_team = ttk.Entry(self, width=30)
        self.entry_team.grid(row=3, column=1, padx=10, pady=5, sticky="w")

        # Buttons
        self.btn_display = ttk.Button(self, text="Display", command=self.on_display)
        self.btn_display.grid(row=4, column=0, padx=10, pady=15, sticky="e")

        self.btn_update = ttk.Button(self, text="Update", command=self.on_update)
        self.btn_update.grid(row=4, column=1, padx=10, pady=15, sticky="w")

        # Status bar
        self.status = tk.StringVar(value="Ready")
        self.statusbar = ttk.Label(self, textvariable=self.status, relief="sunken", anchor="w")
        self.statusbar.grid(row=5, column=0, columnspan=2, sticky="ew")
        self.columnconfigure(1, weight=1)

    def _get_id_int(self) -> int | None:
        val = self.entry_id.get().strip()
        if not val.isdigit():
            messagebox.showerror("Invalid ID", "Please enter a numeric ID (integer).")
            return None
        return int(val)

    def on_display(self):
        fan_id = self._get_id_int()
        if fan_id is None:
            return
        try:
            row = self.repo.fetch_fan_by_id(fan_id)
        except Exception as e:
            messagebox.showerror("Database Error", f"Could not fetch record: {e}")
            self.status.set("Error fetching record")
            return

        if not row:
            messagebox.showinfo("Not Found", f"No record found with ID={fan_id}")
            self.entry_first.delete(0, tk.END)
            self.entry_last.delete(0, tk.END)
            self.entry_team.delete(0, tk.END)
            self.status.set(f"ID {fan_id} not found")
            return

        # row: (id, firstname, lastname, favoriteteam)
        _, firstname, lastname, favoriteteam = row
        self.entry_first.delete(0, tk.END)
        self.entry_first.insert(0, firstname or "")

        self.entry_last.delete(0, tk.END)
        self.entry_last.insert(0, lastname or "")

        self.entry_team.delete(0, tk.END)
        self.entry_team.insert(0, favoriteteam or "")

        self.status.set(f"Displayed ID {fan_id}")

    def on_update(self):
        fan_id = self._get_id_int()
        if fan_id is None:
            return

        firstname = self.entry_first.get().strip()
        lastname = self.entry_last.get().strip()
        favoriteteam = self.entry_team.get().strip()

        if not firstname or not lastname or not favoriteteam:
            messagebox.showwarning("Missing Data", "First, Last, and Favorite Team are required.")
            return

        try:
            updated = self.repo.update_fan(fan_id, firstname, lastname, favoriteteam)
        except Exception as e:
            messagebox.showerror("Database Error", f"Could not update record: {e}")
            self.status.set("Error updating record")
            return

        if updated:
            messagebox.showinfo("Success", f"Record ID {fan_id} updated.")
            self.status.set(f"Updated ID {fan_id}")
        else:
            messagebox.showwarning("Not Found", f"No record with ID {fan_id} to update.")
            self.status.set(f"No update for ID {fan_id}")

if __name__ == "__main__":
    # Run the GUI with default DBConfig per assignment
    app = FansApp(repo=FansRepository(DBConfig()))
    app.mainloop()
