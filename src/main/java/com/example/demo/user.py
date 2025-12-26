#!/usr/bin/env python3

import os
import shutil
import time

SOURCE_DIR = "/home/coder/Workspace/demo/src/test"
DEST_DIR = "/home/coder/Workspace/test_saved"

print("Watching:", SOURCE_DIR)

while True:
    if os.path.isdir(SOURCE_DIR):
        shutil.copytree(SOURCE_DIR, DEST_DIR, dirs_exist_ok=True)
        print("Folder copied")
    else:
        print("Source folder not found")

    time.sleep(2)
