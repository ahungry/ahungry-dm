# https://www.gulshansingh.com/posts/how-to-write-a-display-manager/
xephyr-start:
	Xephyr -ac -br -noreset -screen 800x600 :1

xephyr-run:
	DISPLAY=:1 ./bin/ahungry-dm
