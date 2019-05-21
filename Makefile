# https://www.gulshansingh.com/posts/how-to-write-a-display-manager/
xephyr-start:
	Xephyr -ac -br -noreset -screen 800x600 :1

xephyr-run:
	DISPLAY=:1 ./bin/ahungry-dm

/usr/lib/systemd/system/ahungry-dm.service:
	sudo cp doc/ahungry-dm.service /usr/lib/systemd/system/

install-service: /usr/lib/systemd/system/ahungry-dm.service

install: install-service

.PHONY: install-service install
