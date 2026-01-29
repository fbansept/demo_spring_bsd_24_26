import { useEffect } from "react";
import "./App.css";
import { useState } from "react";

function App() {
  const [listeTicket, setListeTicket] = useState([]);

  function refreshTickets() {
    fetch("http://localhost:8080/ticket/list")
      .then((response) => response.json())
      .then((tickets) => setListeTicket(tickets));
  }

  useEffect(() => {
    refreshTickets();
  }, []);

  function onSuppressionTicket(idTicket) {
    fetch(`http://localhost:8080/ticket/${idTicket}`, {
      method: "DELETE",
    }).then(() => {
      refreshTickets();
    });
  }

  return (
    <>
      <h1>Liste des tickets</h1>
      <ul>
        {listeTicket.map((ticket) => (
          <li key={ticket.id}>
            <h2>{ticket.name}</h2>
            <p>{ticket.description}</p>
            <button onClick={() => onSuppressionTicket(ticket.id)}>x</button>
          </li>
        ))}
      </ul>
    </>
  );
}

export default App;
