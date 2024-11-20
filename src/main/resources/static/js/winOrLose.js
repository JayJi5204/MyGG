
    function updateResults() {
        const winOrLose1 = document.getElementById('winOrLose1');
        const winOrLose2 = document.getElementById('winOrLose2');
        const resultWinOrLose = document.getElementById('resultWinOrLose');

        // 승/패1에 따라 승/패2 및 결과 업데이트
        if (winOrLose1.value === "WIN") {
            winOrLose2.value = "LOSE";
            resultWinOrLose.value = "WIN";
        } else if (winOrLose1.value === "LOSE") {
            winOrLose2.value = "WIN";
            resultWinOrLose.value = "LOSE";
        } else {
            // 초기화
            winOrLose2.value = "";
            resultWinOrLose.value = "";
        }

        // 승/패2가 변경될 때
        if (winOrLose2.value === "WIN") {
            winOrLose1.value = "LOSE";
            resultWinOrLose.value = "LOSE";
        } else if (winOrLose2.value === "LOSE") {
            winOrLose1.value = "WIN";
            resultWinOrLose.value = "WIN";
        }
    }
