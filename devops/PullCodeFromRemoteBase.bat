@echo on
@echo Starting pull code from remote base

@echo Enter the bat file current directory
cd %cd%
cd %~dp0

@echo Starting stash save local code modify
git stash save
@echo Stash save complete

@echo Starting pull code from the remote base
git pull --all
@echo Pull code complete

@echo Auto exit
exit
