"""
Crystal Long
Date: 2025-09-29
Assignment: Fans DB Viewer/Updater (Python + MySQL)
Purpose: Provide a small data-access layer for viewing and updating records in the 'fans' table.
"""

from dataclasses import dataclass
from typing import Optional, Tuple
import mysql.connector
from mysql.connector.connection import MySQLConnection
from mysql.connector.cursor import MySQLCursor

# NOTE: the table/database already exists in the grader's environment.
# This code will not create or delete tables. It performs SELECT and UPDATE only.

@dataclass
class DBConfig:
    host: str = "127.0.0.1"
    database: str = "databasedb"
    user: str = "student1"
    password: str = "pass"
    port: int = 3307

class FansRepository:
    """Encapsulates DB operations for the 'fans' table."""

    def __init__(self, config: Optional[DBConfig] = None):
        self.config = config or DBConfig()

    def _connect(self) -> MySQLConnection:
        """Create and return a new MySQL connection."""
        return mysql.connector.connect(
            host=self.config.host,
            database=self.config.database,
            user=self.config.user,
            password=self.config.password,
            port=self.config.port,
        )

    def fetch_fan_by_id(self, fan_id: int) -> Optional[Tuple[int, str, str, str]]:
        """Return a fan row by ID or None if not found."""
        query = "SELECT id, firstname, lastname, favoriteteam FROM fans WHERE id = %s"
        conn = self._connect()
        try:
            cur: MySQLCursor = conn.cursor()
            cur.execute(query, (fan_id,))
            row = cur.fetchone()
            return row if row else None
        finally:
            try:
                cur.close()
            except Exception:
                pass
            conn.close()

    def update_fan(self, fan_id: int, firstname: str, lastname: str, favoriteteam: str) -> bool:
        """Update an existing record. Returns True if a row was updated."""
        # Defensive: trim and limit by assignment field sizes
        firstname = firstname.strip()[:25]
        lastname = lastname.strip()[:25]
        favoriteteam = favoriteteam.strip()[:25]

        query = "UPDATE fans SET firstname = %s, lastname = %s, favoriteteam = %s WHERE id = %s"
        conn = self._connect()
        try:
            cur: MySQLCursor = conn.cursor()
            cur.execute(query, (firstname, lastname, favoriteteam, fan_id))
            conn.commit()
            return cur.rowcount == 1
        finally:
            try:
                cur.close()
            except Exception:
                pass
            conn.close()
