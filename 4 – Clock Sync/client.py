import threading
import datetime
import socket
import time
from dateutil import parser

class ClockClient:
    def __init__(self, server_address='localhost', server_port=8080):
        self.server_address = server_address
        self.server_port = server_port
        self.client_socket = socket.socket()
        
        print(f"[CLIENT INIT] Clock Client initialized for server at {self.server_address}:{self.server_port}")

    def connect(self):
        try:
            self.client_socket.connect((self.server_address, self.server_port))
            print(f"[CLIENT CONNECTED] Connected successfully to server at {self.server_address}:{self.server_port}")
            
            threading.Thread(target=self._send_time).start()
            threading.Thread(target=self._receive_synced_time).start()
        
        except Exception as e:
            print(f"[ERROR] Could not connect to server: {e}")

    def _send_time(self):
        while True:
            try:
                current_time = datetime.datetime.now()
                self.client_socket.send(str(current_time).encode())
                print(f"[TIME SENT] Current time sent to server: {current_time}")
            except Exception as e:
                print(f"[ERROR] Error sending time to server: {e}")
                break
            time.sleep(5)

    def _receive_synced_time(self):
        while True:
            try:
                synced_time_string = self.client_socket.recv(1024).decode()
                if not synced_time_string:
                    raise ConnectionError("No data received. Server might have disconnected.")
                
                synced_time = parser.parse(synced_time_string)
                print(f"[SYNC RECEIVED] Synchronized time received from server: {synced_time}")
            except Exception as e:
                print(f"[ERROR] Error receiving synchronized time from server: {e}")
                break

if __name__ == '__main__':
    client = ClockClient()
    client.connect()
