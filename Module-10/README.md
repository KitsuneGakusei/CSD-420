# Fans DB Viewer/Updater (Python + MySQL)

## Quick Start
1. Install dependencies:
   ```bash
   pip install mysql-connector-python
   ```

2. Ensure you have a MySQL server reachable at `localhost:3306` with:
   - database: `databasedb`
   - user: `student1`
   - password: `pass`

   For local practice only (not in grader's env), you may run `sample_setup.sql` to create and seed the table.

3. Run the GUI:
   ```bash
   python fans_app.py
   ```

4. Run tests:
   ```bash
   python -m unittest -v
   ```

## Files
- `fans_db.py` – Data access layer.
- `fans_app.py` – Tkinter GUI (Display/Update).
- `test_fans.py` – Unit tests for repository + basic GUI wiring.
- `sample_setup.sql` – Optional local SQL to create/populate table for practice.

## Notes
- The app **does not** create or delete tables, per assignment.
- All queries are parameterized to avoid SQL injection.
- Field inputs are trimmed and capped at 25 characters to match schema.
