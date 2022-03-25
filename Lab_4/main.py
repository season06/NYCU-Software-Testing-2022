from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

driver = webdriver.Chrome(executable_path=ChromeDriverManager().install())

# launch browser and navigate to NYCU home page(https://www.nycu.edu.tw/)  → maximize the window → click 消息 → click first news → print the title and content
def navigate_nycu():
    driver.get("https://www.nycu.edu.tw/")
    driver.maximize_window()

    driver.find_element(By.XPATH, """//*[@id="menu-1-9942884"]/li[2]/a""").click()
    driver.find_element(By.XPATH, """//*[@id="-tab"]/ul/li[1]/a""").click()

    title_ele = driver.find_element(By.XPATH, """/html/body/div[1]/div/main/div/div/div/article/header/h1""")
    content_eles = driver.find_elements(By.XPATH, """/html/body/div[1]/div/main/div/div/div/article/div/p""")

    print("Title: \n", title_ele.text)
    print("Content: ")
    for element in content_eles:
        print(element.text)


# open a new tab and switch to it → navigate to google(https://www.google.com) → input your student number and submit → print the title of second result → close the browser 
def navigate_google():
    driver.execute_script("""window.open("https://www.google.com", "_blank");""")
    driver.switch_to.window(driver.window_handles[1])

    search_ele = driver.find_element(By.NAME, "q")
    search_ele.send_keys("310551019")
    search_ele.send_keys(Keys.ENTER)

    title_ele = WebDriverWait(driver, 10).until(lambda self: driver.find_element(By.XPATH, """//*[@class="g tF2Cxc"][2]/div/div[1]/div/a/h3"""))
    print("Title: \n", title_ele.text)


def main():
    try:
        navigate_nycu()
        navigate_google()

    except Exception as e:
        print(e)

    finally:
        driver.quit() # close windows

if __name__ == '__main__':
    main()