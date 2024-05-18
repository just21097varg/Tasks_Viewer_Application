import { useEffect, useState } from "react";
import Popup from 'reactjs-popup';

function DailyStats(props) {
  var value = props.value;
  console.log(value);
  var user = props.user;
  const [strike, setStrike] = useState({display:'block'});
  const [buttonAction, setButtonAction] = useState({});
  const [task,setTask] = useState('');
  
  const handleTask = (event) => {
    setTask(event.target.value);
  };

  useEffect(() => {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://127.0.0.1:8082/tasks/userId');

    var jsonData = {
      "username": user
    };
    const data = fetch('http://127.0.0.1:8082/tasks/userId', {
      method: 'POST',
      body: JSON.stringify(jsonData),
      headers: headers
    }).then(response => response.json())
      .catch(error => console.error(error));


      value = data;
      console.log(value);
  },[value]);
  

  const taskSubmit = () =>{
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://127.0.0.1:8082/tasks/addTask');

    var jsonData = {
      "username": user,
      "taskDesc": task
    };

    const data = fetch('http://127.0.0.1:8082/tasks/addTask', {
      method: 'POST',
      body: JSON.stringify(jsonData),
      headers: headers
    }).then(response => response.json())
      .catch(error => console.error(error));


      headers.append('Access-Control-Allow-Origin', 'http://127.0.0.1:8082/tasks/userId');

      var jsonData = {
        "username": user
      };
      const data1 = fetch('http://127.0.0.1:8082/tasks/userId', {
        method: 'POST',
        body: JSON.stringify(jsonData),
        headers: headers
      }).then(response => response.json())
        .catch(error => console.error(error));
  
  
        value = data1;
        console.log(value);  

  };

  const strikeout = (id) => (event) => {
    console.log(id);
    console.log(event.target.value);
    setStrike({ 'text-decoration': "line-through", color: 'red' });
    setButtonAction({ 'pointer-events': 'none' })

  }


  return (
    <div>
      <input type='text' value={task} onChange={handleTask}></input>
      <button onClick={taskSubmit}>ADD</button>
      
          <button id='removeTasks' >REMOVE</button>
      <div className="tableDiv">
        {value != '' ?
          <>
            {/* <tr key={"header"}>
              {Object.keys(value[0]).map((key) => (
                <th>{key}</th>
              ))
              }
              <th>UpdateStatus</th>

            </tr> */}
            {value.map((item) => (
              <div className="task-div" style={strike} key={item.taskID}>
                {Object.values(item).map((val) => (
                  <div>{val}</div>
                ))}
                <div className="status-button" onClick={strikeout(item.taskID)}>Done</div>
              </div>
            ))}
          </> : <></>}
          
      </div>
    </div>
  );

}
export default DailyStats;