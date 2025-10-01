"""
Crystal Long
Date: 2025-09-29
Assignment: Fans DB Viewer/Updater (Python + MySQL)
Purpose: Unit tests for data-access methods and basic GUI wiring.
"""

import unittest
from unittest.mock import patch, MagicMock
import os

from fans_db import FansRepository, DBConfig
# We won't import the GUI at top-level to avoid requiring Tk on headless CI;
# we import it inside the test that needs it.

class TestFansRepository(unittest.TestCase):
    @patch("fans_db.mysql.connector.connect")
    def test_fetch_found(self, mock_connect):
        # Arrange fake cursor/connection
        fake_conn = MagicMock()
        fake_cur = MagicMock()
        fake_cur.fetchone.return_value = (1, "Ada", "Lovelace", "Analytical")
        fake_conn.cursor.return_value = fake_cur
        mock_connect.return_value = fake_conn

        repo = FansRepository(DBConfig())

        # Act
        row = repo.fetch_fan_by_id(1)

        # Assert
        self.assertIsNotNone(row)
        self.assertEqual(row[1], "Ada")
        fake_cur.execute.assert_called_once()

    @patch("fans_db.mysql.connector.connect")
    def test_fetch_not_found(self, mock_connect):
        fake_conn = MagicMock()
        fake_cur = MagicMock()
        fake_cur.fetchone.return_value = None
        fake_conn.cursor.return_value = fake_cur
        mock_connect.return_value = fake_conn

        repo = FansRepository()
        row = repo.fetch_fan_by_id(9999)
        self.assertIsNone(row)

    @patch("fans_db.mysql.connector.connect")
    def test_update_success(self, mock_connect):
        fake_conn = MagicMock()
        fake_cur = MagicMock()
        fake_cur.rowcount = 1
        fake_conn.cursor.return_value = fake_cur
        mock_connect.return_value = fake_conn

        repo = FansRepository()
        ok = repo.update_fan(1, "Grace", "Hopper", "Compilers")
        self.assertTrue(ok)
        fake_conn.commit.assert_called_once()

    @patch("fans_db.mysql.connector.connect")
    def test_update_no_row(self, mock_connect):
        fake_conn = MagicMock()
        fake_cur = MagicMock()
        fake_cur.rowcount = 0
        fake_conn.cursor.return_value = fake_cur
        mock_connect.return_value = fake_conn

        repo = FansRepository()
        ok = repo.update_fan(777, "No", "Body", "None")
        self.assertFalse(ok)

class TestGUIWiring(unittest.TestCase):
    def test_gui_widgets_exist(self):
        # Import lazily to avoid issues on systems without a display server.
        import fans_app
        app = fans_app.FansApp(repo=MagicMock())
        try:
            self.assertIsNotNone(app.entry_id)
            self.assertIsNotNone(app.entry_first)
            self.assertIsNotNone(app.entry_last)
            self.assertIsNotNone(app.entry_team)
            self.assertIsNotNone(app.btn_display)
            self.assertIsNotNone(app.btn_update)
        finally:
            app.destroy()

if __name__ == "__main__":
    unittest.main()
